package com.achan.ai_learning_profile_backend.service;
import com.achan.ai_learning_profile_backend.dto.UserCreateRequest;
import com.achan.ai_learning_profile_backend.entity.User;
import com.achan.ai_learning_profile_backend.repository.UserRepository;
import com.achan.ai_learning_profile_backend.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务层
 * 负责处理用户相关的业务逻辑
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserVO> listUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::toUserVO)
                .toList();
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


    public UserVO getUserById(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            return null;
        }

        return toUserVO(user);
    }

    public void createUser(UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());
        user.setRole("USER");
        user.setStatus(1);

        userRepository.save(user);
    }
}