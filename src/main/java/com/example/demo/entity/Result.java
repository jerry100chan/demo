package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4565437360016825796L;

    private final String msg;
    private final int code;
    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result<Object> success(String msg) {
        return create(200, msg);
    }
    public static Result<Object> success() {
        return create(200, "success");
    }
    public static Result<Object> error(String msg) {
        return create(500, msg);
    }

    public static Result<Object> create(int code, String msg) {
        Result<Object> result = new Result<>(code, msg);
        result.setData("");
        return result;
    }
}
