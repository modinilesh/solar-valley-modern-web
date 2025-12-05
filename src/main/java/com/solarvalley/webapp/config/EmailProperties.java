package com.solarvalley.webapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
public class EmailProperties {

    private String from;
    private String to;
    private boolean enabled;
}
