package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo.entity.Result;
import com.example.demo.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserInfoController")
@RequestMapping("user-info")
@RestController
public class UserInfoController {

    @Autowired
    private ServiceHelper serviceHelper;

    @GetMapping(value = "profile/all", produces="application/json")
    public Result<Object> profile() {
        return serviceHelper.getProfiles();
    }

}
