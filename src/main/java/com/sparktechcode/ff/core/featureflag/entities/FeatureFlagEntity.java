package com.sparktechcode.ff.core.featureflag.entities;

import com.sparktechcode.ff.core.featureflag.FeatureFlag;
import com.sparktechcode.springcrud.entities.AutoIdBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Entity
@Table(
    name = "feature_flags",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class FeatureFlagEntity extends AutoIdBaseEntity<String> implements FeatureFlag {

    @Column(name = "name")
    private String  name;

    @Column(name = "description")
    private String  description;

    @Column(name = "mode")
    private String  mode;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "data", length = 4000)
    private String data;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "users", columnDefinition = "jsonb")
    private List<String> users;

    @Column(name = "regex_pattern")
    private String regexPattern;
}