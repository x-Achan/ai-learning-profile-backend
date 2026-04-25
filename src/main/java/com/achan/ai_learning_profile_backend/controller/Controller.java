package com.achan.ai_learning_profile_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. 声明这是一个处理 HTTP 请求的 API 控制器
@RestController
// 2. 映射请求的 URL 路径
@RequestMapping("/hello")
public class Controller {

    // 3. 拦截 GET 方式的请求
    @GetMapping
    public String sayHello() {
        return "AI Learning Profile Platform is running!";
    }
}