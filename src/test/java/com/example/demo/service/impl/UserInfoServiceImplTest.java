package com.example.demo.service.impl;

import com.example.demo.config.UrlProperties;
import com.example.demo.entity.Address;
import com.example.demo.entity.Post;
import com.example.demo.entity.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceImplTest {

    @Mock
    private ThreadPoolTaskExecutor taskExecutor;
    @Mock
    private UrlProperties urlProperties;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private UserInfoServiceImpl userInfoService;


    @Test
    void getUserInfoListFuture() {
        Future<ResponseEntity<List<UserInfo>>> postListFuture = userInfoService.getUserInfoListFuture();
        Mockito.verify(urlProperties, times(1)).getUserInfo();
    }

    @Test
    void getUserInfoList() throws Exception {
        Future<ResponseEntity<List<UserInfo>>> userInfoListFuture = mock(Future.class);
        List<UserInfo> list = getUserInfos();

        ResponseEntity<List<UserInfo>> response = ResponseEntity.status(HttpStatus.OK).body(list);
        when(userInfoListFuture.get()).thenReturn(response);
        List<UserInfo> postListEntity = userInfoService.getUserInfoList(userInfoListFuture);
        Assertions.assertEquals(1, postListEntity.size());
    }

    public static List<UserInfo> getUserInfos() {
        List<UserInfo> list = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        Address address = new Address();
        address.setStreet("street no 2");
        address.setSuite("suite 3");
        address.setCity("beijing");
        address.setZipcode("350032");
        userInfo.setAddress(address);
        userInfo.setUsername("li ming");
        list.add(userInfo);
        return list;
    }

    // TODO need to test more for exception cases
}