package com.example.demo.util;

import com.example.demo.entity.Post;
import com.example.demo.entity.Result;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.PostService;
import com.example.demo.service.UserInfoService;
import com.example.demo.service.impl.PostServiceImplTest;
import com.example.demo.service.impl.UserInfoServiceImplTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceHelperTest {

    @Mock
    private UserInfoService userInfoService;
    @Mock
    private PostService postService;
    @InjectMocks
    private ServiceHelper serviceHelper;

    @Test
    void getProfiles() {
        List<UserInfo> userInfos = UserInfoServiceImplTest.getUserInfos();
        List<Post> posts = PostServiceImplTest.getPosts();
        when(userInfoService.getUserInfoList(any())).thenReturn(userInfos);
        when(postService.getPostListEntity(any())).thenReturn(posts);
        Result<Object> profiles = serviceHelper.getProfiles();
        Assertions.assertNotNull(profiles);
        Assertions.assertEquals(profiles.getCode(), HttpStatus.OK.value());

        Object data = profiles.getData();
        if (data instanceof List) {
            Assertions.assertEquals(((List) data).size(), userInfos.size());
        }
    }

    // TODO need to test more for exception cases
}