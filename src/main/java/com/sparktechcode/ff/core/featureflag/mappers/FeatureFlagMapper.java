package com.sparktechcode.ff.core.featureflag.mappers;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.ff.core.featureflag.payloads.FeatureFlagRequestDto;
import com.sparktechcode.ff.core.featureflag.payloads.FeatureFlagResponseDto;
import com.sparktechcode.springcrud.mappers.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureFlagMapper extends CrudMapper<String, FeatureFlagRequestDto, FeatureFlagEntity, FeatureFlagResponseDto>, FeatureFlag {
}