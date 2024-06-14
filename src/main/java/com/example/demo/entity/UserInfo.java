package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 985947397875253938L;

    private Long id;
    private String username;
    private Address address;

}
