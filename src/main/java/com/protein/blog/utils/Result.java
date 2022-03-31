package com.protein.blog.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Result<T>{

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAILED = 1;

    private int code;

    private Map<String, String> msg;

    private T data;

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, Map<String, String> msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Map<String, String> msg(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    public static Result of(boolean b) {
        return b ? success() : failed();
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, null, data);
    }

    public static Result success() {
        return new Result(CODE_SUCCESS, null, null);
    }

    public static Result success(String key, String value) {
        return success(null, msg(key, value));
    }

    public static Result success(Object data, Map<String, String> msg) {
        return new Result(CODE_SUCCESS, msg, data);
    }

    public static Result failed(Map<String, String> msg) {
        return new Result(CODE_FAILED, msg, null);
    }

    public static Result failed() {
        return new Result(CODE_FAILED, null, null);
    }

    public static Result failed(Map<String, String> msg, Object bean) {
        return new Result(CODE_FAILED, msg, bean);
    }

    public static Result failed(Map<String, String> msg, String bean) {
        return new Result(CODE_FAILED, msg, bean);
    }

    public static Result failed(Errors errors, Object bean) {
        return failed(buildMsg(errors), bean);
    }

    public static Result failed(String errorMsg) {
        HashMap<String, String> map = new HashMap<>();
        map.put("error", errorMsg);
        return failed(map, null);
    }

    public static Map buildMsg(Errors errors) {
        List<ObjectError> oe = errors.getAllErrors();
        Map<String, String> map = new HashMap<>();
        for (ObjectError e : oe) {
            map.put(((FieldError) e).getField(), e.getDefaultMessage());
        }
        return map;
    }

}

