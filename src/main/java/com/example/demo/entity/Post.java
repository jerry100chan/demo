package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1747134517815871766L;

    private Long userId;
    private Long id;
    private String title;

}
