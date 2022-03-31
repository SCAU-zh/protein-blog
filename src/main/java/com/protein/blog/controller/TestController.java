package com.protein.blog.controller;

import com.protein.blog.utils.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: TestController
 * @Author LinZihong
 * @Date: 2022/3/31 2:41 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/success")
    public Result success(){
        return Result.success("欢迎光临");
    }
}
