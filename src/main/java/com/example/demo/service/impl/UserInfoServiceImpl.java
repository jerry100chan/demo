package com.example.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.config.UrlProperties;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;
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
public class UserInfoServiceImpl implements UserInfoService {

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
    public Future<ResponseEntity<List<UserInfo>>> getUserInfoListFuture() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ACCEPT, CONTENT_TYPE);
        HttpEntity<MultiValueMap<String, String>> userInfoEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<UserInfo>> userInfoResponseType = new ParameterizedTypeReference<>() {};
        String userInfoURL = urlProperties.getUserInfo();
        return taskExecutor.submit(() -> restTemplate.exchange(userInfoURL, HttpMethod.GET, userInfoEntity, userInfoResponseType));
    }

    @Override
    public List<UserInfo> getUserInfoList(Future<ResponseEntity<List<UserInfo>>> userInfoListFuture) {
        List<UserInfo> userInfoList = new ArrayList<>();
        try {
            ResponseEntity<List<UserInfo>> userInfoListEntity = userInfoListFuture.get();
            if (userInfoListEntity != null) {
                HttpStatusCode httpStatusCode = userInfoListEntity.getStatusCode();
                List<UserInfo> userInfos = userInfoListEntity.getBody();
                if (httpStatusCode.is2xxSuccessful()) {
                    if (userInfos != null) {
                        userInfoList = userInfos;
                    }
                } else {
                    String body = "";
                    if (userInfos != null && !userInfos.isEmpty()) {
                        body = objectMapper.writeValueAsString(body);
                    }
                    log.warn("Getting user info interface went wrong, code: {}, body: {}", httpStatusCode.value(), body);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return userInfoList;
    }
}
