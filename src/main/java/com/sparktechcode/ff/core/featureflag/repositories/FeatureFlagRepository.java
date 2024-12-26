package com.sparktechcode.ff.core.featureflag.repositories;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;

public interface FeatureFlagRepository extends SparkJpaRepository<String, FeatureFlagEntity>, FeatureFlag {
}