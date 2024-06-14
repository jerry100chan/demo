package com.example.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.config.UrlProperties;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static com.example.demo.util.Constant.ACCEPT;
import static com.example.demo.util.Constant.CONTENT_TYPE;

@Slf4j
@Component
public class PostServiceImpl implements PostService {

    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private UrlProperties urlProperties;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Future<ResponseEntity<List<Post>>> getPostListFuture() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ACCEPT, CONTENT_TYPE);
        ParameterizedTypeReference<List<Post>> postResponseType = new ParameterizedTypeReference<>() {};
        String postURL = urlProperties.getPost();
        HttpEntity<MultiValueMap<String, String>> postEntity = new HttpEntity<>(headers);
        return taskExecutor.submit(() -> restTemplate.exchange(postURL, HttpMethod.GET, postEntity, postResponseType));
    }

    @Override
    public List<Post> getPostListEntity(Future<ResponseEntity<List<Post>>> postListFuture) {
        List<Post> postList = new ArrayList<>();
        try {
            ResponseEntity<List<Post>> postListEntity = postListFuture.get();
            if (postListEntity != null) {
                HttpStatusCode httpStatusCode = postListEntity.getStatusCode();
                List<Post> posts = postListEntity.getBody();
                if (httpStatusCode.is2xxSuccessful()) {
                    if (posts != null) {
                        postList = posts;
                    }
                } else {
                    String body = "";
                    if (posts != null && !posts.isEmpty()) {
                        body = objectMapper.writeValueAsString(body);
                    }
                    log.warn("Getting user post interface  went wrong, code: {}, body: {}", httpStatusCode.value(), body);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return postList;
    }
}
