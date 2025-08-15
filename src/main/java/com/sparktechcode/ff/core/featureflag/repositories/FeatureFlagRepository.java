package com.sparktechcode.ff.core.featureflag.repositories;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeatureFlagRepository extends SparkJpaRepository<String, FeatureFlagEntity>, FeatureFlag {

    @Modifying
    @Query(value = "NOTIFY refresh_broadcast", nativeQuery = true)
    void refreshBroadcast();
}