package com.sparktechcode.ff.core.featureflag.services;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.featureflag.entities.FeatureFlagEntity;
import com.sparktechcode.ff.core.featureflag.repositories.FeatureFlagRepository;
import com.sparktechcode.springcrud.exceptions.NotFoundException;
import com.sparktechcode.springcrud.services.CrudService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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
        featureFlags = repository.findAll();
        featureFlags.sort(comparing(FeatureFlagEntity::getCreated).reversed());
    }

    public List<FeatureFlagEntity> getFeatureFlags(String userId) {
        if (userId != null) {
            return featureFlags.stream()
                    .filter(item -> isUserEligibleForFeatureFlag(item, userId))
                    .collect(Collectors.toList());
        }
        return featureFlags;
    }

    public FeatureFlagEntity getFeatureFlagByName(String name, String userId) {
        return featureFlags.stream()
                .filter(f -> f.getName().equals(name) && isUserEligibleForFeatureFlag(f, userId))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public FeatureFlagEntity create(FeatureFlagEntity entity) {
        var savedEntity = CrudService.super.create(entity);
        featureFlags.add(entity);
        featureFlags.sort(comparing(FeatureFlagEntity::getCreated).reversed());
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
        featureFlags.sort(comparing(FeatureFlagEntity::getCreated).reversed());
        return savedEntity;
    }

    @Override
    public FeatureFlagEntity remove(FeatureFlagEntity entity) {
        var removedEntity = CrudService.super.remove(entity);
        featureFlags.removeIf(element -> element.getId().equals(entity.getId()));
        return removedEntity;
    }

    private boolean isUserEligibleForFeatureFlag(FeatureFlagEntity featureFlag, String userId) {
        var users = featureFlag.getUsers();
        if (users != null && !users.isEmpty() && userId != null && featureFlag.getEnabled()) {
            return users.stream().anyMatch(user -> userId.equals(user) || userId.matches(user));
        }
        return true;
    }
}