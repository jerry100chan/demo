package com.example.demo.service;

import com.example.demo.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.Future;

public interface PostService {

    Future<ResponseEntity<List<Post>>> getPostListFuture();

    List<Post> getPostListEntity(Future<ResponseEntity<List<Post>>> postListFuture);
}
