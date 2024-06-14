package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfiguration {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(TaskPoolThreadProperties threadProperties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadProperties.getMaxPoolSize());
        executor.setQueueCapacity(threadProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(threadProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix("TaskExecutePool-");
        // reject policy: by caller itself
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // execute all tasks before it shuts down
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();

        return executor;
    }
}
