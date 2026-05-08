package com.achan.ai_learning_profile_backend.service;

import com.achan.ai_learning_profile_backend.dto.UserCreateRequest;
import com.achan.ai_learning_profile_backend.dto.UserRegisterRequest;
import com.achan.ai_learning_profile_backend.dto.UserUpdateRequest;
import com.achan.ai_learning_profile_backend.entity.User;
import com.achan.ai_learning_profile_backend.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.achan.ai_learning_profile_backend.common.PageResult;
import com.achan.ai_learning_profile_backend.dto.UserLoginRequest;

import java.util.List;

public interface UserService extends IService<User> {

    List<UserVO> listUsers();

    UserVO getUserById(Long id);

    void createUser(UserCreateRequest request);

    UserVO register(UserRegisterRequest request);

    UserVO updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);

    PageResult<UserVO> pageUsers(Long page, Long size);

    UserVO login(UserLoginRequest request);
}