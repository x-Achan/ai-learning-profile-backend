package com.achan.ai_learning_profile_backend.controller;

import com.achan.ai_learning_profile_backend.common.Result;
import com.achan.ai_learning_profile_backend.dto.UserCreateRequest;
import com.achan.ai_learning_profile_backend.service.UserService;
import com.achan.ai_learning_profile_backend.vo.UserVO;
import org.springframework.web.bind.annotation.*;
import com.achan.ai_learning_profile_backend.dto.UserRegisterRequest;
import com.achan.ai_learning_profile_backend.dto.UserUpdateRequest;
import com.achan.ai_learning_profile_backend.common.PageResult;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import com.achan.ai_learning_profile_backend.dto.UserLoginRequest;

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
        return Result.success(userService.getUserById(id));
    }

    @PostMapping("/api/users")
    public Result<Void> createUser(@Valid @RequestBody UserCreateRequest request) {
        userService.createUser(request);
        return Result.success();
    }

    @PostMapping("/api/user/register")
    public Result<UserVO> register(@Valid @RequestBody UserRegisterRequest request) {
        return Result.success(userService.register(request));
    }

    @GetMapping("/api/user/{id}")
    public Result<UserVO> getUser(@PathVariable Long id) {
        UserVO user = userService.getUserById(id);

        if (user == null) {
            return Result.error(404, "用户不存在");
        }

        return Result.success(user);
    }

    @PutMapping("/api/user/{id}")
    public Result<UserVO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        return Result.success(userService.updateUser(id, request));
    }

    @DeleteMapping("/api/user/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success();

    }

    @GetMapping("/api/user/page")
    public Result<PageResult<UserVO>> pageUsers(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long size
    ) {
        return Result.success(userService.pageUsers(page, size));
    }

    @PostMapping("/api/user/login")
    public Result<UserVO> login(@Valid @RequestBody UserLoginRequest request) {
        return Result.success(userService.login(request));
    }
}