package com.sparktechcode.ff.core.accessibility.featureflag.controllers;

import com.sparktechcode.ff.core.accessibility.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.accessibility.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.ff.core.accessibility.featureflag.mappers.FeatureFlagMapper;
import com.sparktechcode.ff.core.accessibility.featureflag.payloads.FeatureFlagRequestDto;
import com.sparktechcode.ff.core.accessibility.featureflag.payloads.FeatureFlagResponseDto;
import com.sparktechcode.ff.core.accessibility.featureflag.services.FeatureFlagService;
import com.sparktechcode.springcrud.controllers.multiple.SCRUDController;
import com.sparktechcode.springcrud.payloads.PathParams;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<FeatureFlagResponseDto> getAll() {
        return mapper.toDtoList(service.getFeatureFlags(), PathParams.getInstance());
    }

    @GetMapping("reload")
    public List<FeatureFlagResponseDto> reloadAll() {
        service.init();
        return mapper.toDtoList(service.getFeatureFlags(), PathParams.getInstance());
    }
}