package com.sparktechcode.ff.core.accessibility.featureflag.repositories;

import com.sparktechcode.ff.core.accessibility.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.accessibility.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;

public interface FeatureFlagRepository extends SparkJpaRepository<String, FeatureFlagEntity>, FeatureFlag {
}