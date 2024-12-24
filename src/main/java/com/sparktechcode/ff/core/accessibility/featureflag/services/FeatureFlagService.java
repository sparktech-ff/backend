package com.sparktechcode.ff.core.accessibility.featureflag.services;

import com.sparktechcode.ff.core.accessibility.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.accessibility.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.ff.core.accessibility.featureflag.repositories.FeatureFlagRepository;
import com.sparktechcode.springcrud.services.CrudService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class FeatureFlagService implements CrudService<String, FeatureFlagEntity>, FeatureFlag {

    private final FeatureFlagRepository repository;
    private final EntityManager entityManager;

    private static List<FeatureFlagEntity> featureFlags = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        FeatureFlagService.featureFlags = repository.findAll();
    }

    public List<FeatureFlagEntity> getFeatureFlags() {
        return featureFlags;
    }

    @Override
    public FeatureFlagEntity create(FeatureFlagEntity entity) {
        var savedEntity = CrudService.super.create(entity);
        featureFlags.add(entity);
        return savedEntity;
    }

    @Override
    public FeatureFlagEntity update(FeatureFlagEntity entity) {
        var savedEntity = CrudService.super.update(entity);
        for (var flag : featureFlags) {
            if (flag.getId().equals(entity.getId())) {
                featureFlags.set(featureFlags.indexOf(flag), savedEntity);
            }
        }
        return savedEntity;
    }

    @Override
    public FeatureFlagEntity remove(FeatureFlagEntity entity) {
        var removedEntity = CrudService.super.remove(entity);
        featureFlags.removeIf(element -> element.getId().equals(entity.getId()));
        return removedEntity;
    }
}