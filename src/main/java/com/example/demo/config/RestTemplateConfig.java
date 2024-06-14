package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    public static final int READ_TIMEOUT = 24000;
    public static final int CONNECT_TIMEOUT = 30000;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // read time out 24 * 1000ms
        factory.setReadTimeout(READ_TIMEOUT);
        // connect time out 30 * 1000ms
        factory.setConnectTimeout(CONNECT_TIMEOUT); // ms
        return factory;
    }

}
