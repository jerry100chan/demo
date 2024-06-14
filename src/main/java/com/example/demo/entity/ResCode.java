package com.example.demo.entity;

import lombok.Getter;

@Getter
public enum ResCode {
    SUCCESS(200, "success"),
    ERROR(500, "error");

    private final int code;
    private final String msg;

    ResCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
