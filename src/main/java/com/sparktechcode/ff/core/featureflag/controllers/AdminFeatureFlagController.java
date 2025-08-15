package com.sparktechcode.ff.core.featureflag.controllers;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.ff.core.featureflag.mappers.FeatureFlagMapper;
import com.sparktechcode.ff.core.featureflag.payloads.FeatureFlagRequestDto;
import com.sparktechcode.ff.core.featureflag.payloads.FeatureFlagResponseDto;
import com.sparktechcode.ff.core.featureflag.services.FeatureFlagService;
import com.sparktechcode.springcrud.controllers.multiple.SCRUDController;
import com.sparktechcode.springcrud.payloads.PathParams;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter
@RestController
@RequestMapping("admin/feature-flags")
@RequiredArgsConstructor
@Tags(value = {@Tag(name = "Admin | Feature Flags"), @Tag(name= "FeatureFlagWithAdmin")})
public class AdminFeatureFlagController implements
        SCRUDController<String, FeatureFlagRequestDto, FeatureFlagEntity, FeatureFlagResponseDto>, FeatureFlag {

    private final FeatureFlagService service;
    private final FeatureFlagMapper mapper;

    @GetMapping("all")
    public List<FeatureFlagResponseDto> getFeatureFlags(@RequestParam(required = false) String userId) {
        return mapper.toDtoList(service.getFeatureFlags(userId), PathParams.getInstance());
    }

    @GetMapping("reload")
    public List<FeatureFlagResponseDto> reloadAndGetFeatureFlags(@RequestParam(required = false) String userId) {
        service.refresh();
        return mapper.toDtoList(service.getFeatureFlags(userId), PathParams.getInstance());
    }
}