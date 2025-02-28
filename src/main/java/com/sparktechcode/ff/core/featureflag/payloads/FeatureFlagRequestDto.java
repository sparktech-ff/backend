package com.sparktechcode.ff.core.featureflag.payloads;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
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
    private Boolean enabled;
    private String data;
    private List<String> users;
}
