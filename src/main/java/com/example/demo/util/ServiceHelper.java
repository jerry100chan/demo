package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import com.example.demo.entity.Post;
import com.example.demo.entity.Profile;
import com.example.demo.entity.Result;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.PostService;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Slf4j
@Component
public class ServiceHelper {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PostService postService;

    public Result<Object> getProfiles() {
        // request for getting user basic info list
        Future<ResponseEntity<List<UserInfo>>> userInfoListFuture = userInfoService.getUserInfoListFuture();
        // request for getting user post list
        Future<ResponseEntity<List<Post>>> postListFuture = postService.getPostListFuture();
        List<UserInfo> userInfoList = userInfoService.getUserInfoList(userInfoListFuture);
        List<Post> postList = postService.getPostListEntity(postListFuture);

        List<Profile> profileList = createProfiles(postList, userInfoList);

        Result<Object> result = Result.success();
        result.setData(profileList);

        return result;
    }

    private List<Profile> createProfiles(List<Post> postList, List<UserInfo> userInfoList) {
        Map<Long, List<String>> postMap = new HashMap<>();
        for (Post post : postList) {
            Long userId = post.getUserId();
            if (!postMap.containsKey(userId)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(post.getTitle());
                postMap.put(userId, list);
            } else {
                List<String> list = postMap.get(userId);
                list.add(post.getTitle());
            }
        }
        List<Profile> profileList = new ArrayList<>();
        for (UserInfo userInfo : userInfoList) {
            Profile profile = new Profile();
            Long id = userInfo.getId();
            List<String> titleList = postMap.get(id);
            profile = profile.buildWithUserInfo(userInfo).buildWithTitleList(titleList);
            profileList.add(profile);
        }

        return profileList;
    }

}

