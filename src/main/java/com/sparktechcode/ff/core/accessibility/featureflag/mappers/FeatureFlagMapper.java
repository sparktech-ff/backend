package com.sparktechcode.ff.core.accessibility.featureflag.mappers;

import com.sparktechcode.ff.core.accessibility.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.accessibility.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.ff.core.accessibility.featureflag.payloads.FeatureFlagRequestDto;
import com.sparktechcode.ff.core.accessibility.featureflag.payloads.FeatureFlagResponseDto;
import com.sparktechcode.springcrud.mappers.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureFlagMapper extends CrudMapper<String, FeatureFlagRequestDto, FeatureFlagEntity, FeatureFlagResponseDto>, FeatureFlag {
}