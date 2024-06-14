package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@EnableConfigurationProperties(UrlProperties.class)
@ConfigurationProperties(prefix = "url")
public class UrlProperties {
    private String userInfo;
    private String post;
}
