package com.achan.ai_learning_profile_backend.controller;

import com.achan.ai_learning_profile_backend.common.Result;
import com.achan.ai_learning_profile_backend.dto.UserCreateRequest;
import com.achan.ai_learning_profile_backend.service.UserService;
import com.achan.ai_learning_profile_backend.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层
 * - 负责接收用户相关的 HTTP 请求
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public Result<List<UserVO>> listUsers() {
        return Result.success(userService.listUsers());
    }

    @GetMapping("/api/users/{id}")
    public Result<UserVO> getUserById(@PathVariable Long id) {
        UserVO user = userService.getUserById(id);

        if (user == null) {
            return Result.error(404, "用户不存在");
        }

        return Result.success(user);
    }

    @PostMapping("/api/users")
    public Result<Void> createUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
        return Result.success();
    }
}