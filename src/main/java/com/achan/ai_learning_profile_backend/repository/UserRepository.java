package com.achan.ai_learning_profile_backend.repository;

import com.achan.ai_learning_profile_backend.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据访问层
 * 负责执行 SQL 操作 user表
 */
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 查询所有用户
    public List<User> findAll() {
        String sql = """
                SELECT id, username, password, nickname, email, role, status,
                       create_time, update_time, is_deleted
                FROM `user`
                WHERE is_deleted = 0
                ORDER BY id DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            user.setStatus(rs.getInt("status"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
            user.setIsDeleted(rs.getInt("is_deleted"));
            return user;
        });
    }

    //  根据 id 查询单个用户
    public User findById(Long id) {
        String sql = """
            SELECT id, username, password, nickname, email, role, status,
                   create_time, update_time, is_deleted
            FROM `user`
            WHERE id = ? AND is_deleted = 0
            """;

        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            user.setStatus(rs.getInt("status"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
            user.setIsDeleted(rs.getInt("is_deleted"));
            return user;
        }, id);

        return users.isEmpty() ? null : users.get(0);
    }

    // 新增用户
    public int save(User user) {
        String sql = """
            INSERT INTO `user` (username, password, nickname, email, role, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        return jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getNickname(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );
    }
}

