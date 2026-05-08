package com.achan.ai_learning_profile_backend.service.impl;

import com.achan.ai_learning_profile_backend.dto.UserCreateRequest;
import com.achan.ai_learning_profile_backend.entity.User;
import com.achan.ai_learning_profile_backend.mapper.UserMapper;
import com.achan.ai_learning_profile_backend.service.UserService;
import com.achan.ai_learning_profile_backend.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.achan.ai_learning_profile_backend.dto.UserRegisterRequest;
import com.achan.ai_learning_profile_backend.dto.UserUpdateRequest;
import com.achan.ai_learning_profile_backend.common.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.achan.ai_learning_profile_backend.exception.BusinessException;
import com.achan.ai_learning_profile_backend.dto.UserLoginRequest;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<UserVO> listUsers() {
        return this.lambdaQuery()
                .orderByDesc(User::getId)
                .list()
                .stream()
                .map(this::toUserVO)
                .toList();
    }

    @Override
    public UserVO getUserById(Long id) {
        User user = this.getById(id);

        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }

        return toUserVO(user);
    }

    @Override
    public void createUser(UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());
        user.setRole("USER");
        user.setStatus(1);
        user.setIsDeleted(0);

        this.save(user);
    }

    @Override
    public UserVO register(UserRegisterRequest request) {
        boolean usernameExists = this.lambdaQuery()
                .eq(User::getUsername, request.getUsername())
                .count() > 0;

        if (usernameExists) {
            throw new BusinessException(400, "用户名已存在");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());
        user.setRole("USER");
        user.setStatus(1);
        user.setIsDeleted(0);

        this.save(user);

        return toUserVO(user);
    }

    @Override
    public UserVO updateUser(Long id, UserUpdateRequest request) {
        User user = this.getById(id);

        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }

        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());

        this.updateById(user);

        User updatedUser = this.getById(id);
        return toUserVO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.getById(id);

        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }

        this.removeById(id);
    }

    @Override
    public PageResult<UserVO> pageUsers(Long page, Long size) {
        Page<User> pageParam = new Page<>(page, size);

        Page<User> userPage = this.lambdaQuery()
                .orderByDesc(User::getId)
                .page(pageParam);

        List<UserVO> records = userPage.getRecords()
                .stream()
                .map(this::toUserVO)
                .toList();

        return new PageResult<>(
                userPage.getCurrent(),
                userPage.getSize(),
                userPage.getTotal(),
                userPage.getPages(),
                records
        );
    }

    @Override
    public UserVO login(UserLoginRequest request) {
        User user = this.lambdaQuery()
                .eq(User::getUsername, request.getUsername())
                .eq(User::getPassword, request.getPassword())
                .one();

        if (user == null) {
            throw new BusinessException(400, "用户名或密码错误");
        }

        if (user.getStatus() != null && user.getStatus() == 0) {
            throw new BusinessException(403, "用户已被禁用");
        }

        return toUserVO(user);
    }

    private UserVO toUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setNickname(user.getNickname());
        userVO.setEmail(user.getEmail());
        userVO.setRole(user.getRole());
        userVO.setStatus(user.getStatus());
        userVO.setCreateTime(user.getCreateTime());
        return userVO;
    }
}