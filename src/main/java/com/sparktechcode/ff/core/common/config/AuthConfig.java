package com.sparktechcode.ff.core.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("ff.auth")
public class AuthConfig {
    private String adminUserId;
}
