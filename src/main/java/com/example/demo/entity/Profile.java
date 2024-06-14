package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Profile implements Serializable {
    @Serial
    private static final long serialVersionUID = 985947397875253938L;

    private String username;
    private Address address ;
    private List<String> postTitleList;

    public Profile() {
        // do nothing
    }

    public Profile buildWithUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return this;
        }
        this.username = userInfo.getUsername();
        this.address = userInfo.getAddress();
        return this;
    }

    public Profile buildWithTitleList(List<String> postTitleList) {
        if (postTitleList == null) {
            return this;
        }
        this.postTitleList = postTitleList;
        return this;
    }

}
