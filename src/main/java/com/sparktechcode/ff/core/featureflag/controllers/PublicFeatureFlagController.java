package com.sparktechcode.ff.core.featureflag.controllers;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.ff.core.featureflag.mappers.FeatureFlagMapper;
import com.sparktechcode.ff.core.featureflag.payloads.FeatureFlagResponseDto;
import com.sparktechcode.ff.core.featureflag.services.FeatureFlagService;
import com.sparktechcode.springcrud.controllers.SearchController;
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
@RequestMapping("public/feature-flags")
@RequiredArgsConstructor
@Tags(value = {@Tag(name = "Public | Feature Flags"), @Tag(name= "FeatureFlagWithAdmin")})
public class PublicFeatureFlagController implements
        SearchController<String, FeatureFlagEntity, FeatureFlagResponseDto>, FeatureFlag {

    private final FeatureFlagService service;
    private final FeatureFlagMapper mapper;

    @GetMapping("all")
    public List<FeatureFlagResponseDto> getFeatureFlags(@RequestParam(required = false) String userId) {
        return mapper.toDtoList(service.getFeatureFlags(userId), PathParams.getInstance());
    }

    @GetMapping("name")
    public FeatureFlagResponseDto getFeatureFlagByName(@RequestParam String name, @RequestParam(required = false) String userId) {
        return mapper.toDto(service.getFeatureFlagByName(name, userId));
    }
}