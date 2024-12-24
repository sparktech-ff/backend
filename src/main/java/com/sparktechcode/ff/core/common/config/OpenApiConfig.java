package com.sparktechcode.ff.core.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.String.format;

@Slf4j
@Configuration
public class OpenApiConfig {

    static {
        var schema = new Schema<LocalTime>();
        schema.example(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).type("string");
        SpringDocUtils.getConfig().replaceWithSchema(LocalTime.class, schema);
    }

    @Bean
    public OpenAPI customApi() {
        var securitySchemeName = "bearerAuth";
        var schema = new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");
        var component = new Components().addSecuritySchemes(securitySchemeName, schema);
        return new OpenAPI()
                .components(component)
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info().title("Feature Flag 2.0 server").version("v1"));
    }

    @Bean
    OpenApiCustomizer operationIdCustomNames() {
        return openApi -> openApi.getPaths().forEach((path, pathItem) -> {
            pathItem.readOperations().forEach(operation -> {
                setPathParams(path, operation);
                setSummary(operation);
                setOperationId(operation);
            });
        });
    }

    private void setOperationId(Operation operation) {
        var name = operation.getTags().stream().filter(item -> !item.contains("|")).findFirst();
        operation.setTags(operation.getTags().stream().filter(item -> item.contains("|")).toList());
        if (name.isPresent()) {
            var operationId = operation.getOperationId();
            var placeHolder = getPlaceHolder(operationId);
            if (placeHolder != null) {
                operationId = operation.getOperationId().replace(placeHolder, name.get());
                operation.setOperationId(operationId);
            }
        }
    }

    private String getPlaceHolder(String operation) {
        var regex = ".*(_[0-9]+)$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(operation);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private void setSummary(Operation operation) {
        operation.setSummary(getSummary(operation.getTags()));
    }

    private void setPathParams(String path, Operation operation) {
        var params = extractPathVariables(path);
        if (operation.getParameters() == null) {
            operation.setParameters(new ArrayList<>());
        }
        params.forEach(param -> {
            var exists = operation.getParameters().stream().anyMatch(item -> item.getName().equals(param));
            if (!exists) {
                var item = new Parameter();
                item.setName(param);
                item.setIn("path");
                item.setSchema(new Schema<>());
                item.getSchema().setType("string");
                item.getSchema().setExample(param);
                operation.addParametersItem(item);
            }
        });
    }

    public List<String> extractPathVariables(String path) {
        var pathVariables = new ArrayList<String>();
        var pattern = Pattern.compile("\\{([^/{}]+)\\}");
        var matcher = pattern.matcher(path);
        while (matcher.find()) {
            pathVariables.add(matcher.group(1));
        }
        return pathVariables;
    }

    private String getSummary(List<String> tags) {
        var first = tags.stream().findFirst();
        if (first.isPresent()) {
            var name = first.get();
            for (String role : List.of("admin")) {
                if (name.toLowerCase().startsWith(role)) {
                    return format("Required bearer token with %s role.", role);
                }
            }
        }
        return "Bearer token is not required for this endpoint.";
    }
}
