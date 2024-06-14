package com.example.demo.service;

import com.example.demo.entity.UserInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.Future;

public interface UserInfoService {

    Future<ResponseEntity<List<UserInfo>>> getUserInfoListFuture();

    List<UserInfo> getUserInfoList(Future<ResponseEntity<List<UserInfo>>> userInfoListFuture);

}
