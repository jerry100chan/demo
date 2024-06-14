package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "task.pool")
public class TaskPoolThreadProperties {
    private int corePoolSize = 5;
    private int maxPoolSize = 5;
    private int queueCapacity = 100;
    private int keepAliveSeconds = 30;
}
