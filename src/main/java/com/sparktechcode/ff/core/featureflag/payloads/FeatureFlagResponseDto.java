package com.sparktechcode.ff.core.featureflag.payloads;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.ff.core.common.payloads.AuditDto;
import com.sparktechcode.springjpasearch.entities.IdHolder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeatureFlagResponseDto extends AuditDto implements IdHolder<String>, FeatureFlag {

    private String id;
    private String name;
    private String  description;
    private String mode;
    private List<String> users;
}
