package com.example.demo.service.impl;

import com.example.demo.config.UrlProperties;
import com.example.demo.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings({"unchecked", "rawtypes"})
public class PostServiceImplTest {

    @Mock
    private ThreadPoolTaskExecutor taskExecutor;
    @Mock
    private UrlProperties urlProperties;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void getPostListFuture() {
        Future<ResponseEntity<List<Post>>> postListFuture = postService.getPostListFuture();
        Mockito.verify(urlProperties, times(1)).getPost();
    }

    @Test
    void getPostListEntity() throws Exception {
        Future<ResponseEntity<List<Post>>> postListFuture = mock(Future.class);
        List<Post> list = getPosts();

        ResponseEntity<List<Post>> response = ResponseEntity.status(HttpStatus.OK).body(list);
        when(postListFuture.get()).thenReturn(response);
        List<Post> postListEntity = postService.getPostListEntity(postListFuture);
        Assertions.assertEquals(1, postListEntity.size());
    }

    public static List<Post> getPosts() {
        List<Post> list = new ArrayList<>();
        Post post = new Post();
        post.setUserId(1L);
        post.setId(1L);
        post.setTitle("test");
        list.add(post);
        return list;
    }

    // TODO need to test more for exception cases
}