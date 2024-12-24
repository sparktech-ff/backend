package com.sparktechcode.ff.core.accessibility.featureflag.payloads;

import com.sparktechcode.ff.core.accessibility.featureflag.FeatureFlag;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeatureFlagRequestDto implements FeatureFlag {
    @NotNull(message = "Required name")
    private String name;
    private String  description;
    private String mode;
    private List<String> users;
}
