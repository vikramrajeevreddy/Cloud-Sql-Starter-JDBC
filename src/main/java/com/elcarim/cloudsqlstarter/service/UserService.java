package com.elcarim.cloudsqlstarter.service;

import com.elcarim.cloudsqlstarter.model.User;
import com.elcarim.cloudsqlstarter.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM User";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM User WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
    }

    public int addUser(User user) {
        String sql = "INSERT INTO User (uuid_id, firstname, lastname, email, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUuid_id(), user.getFirstname(),user.getLastname(), user.getEmail(), user.getUsername(), user.getPassword());
    }

    public User getUserByUuid(String id) {
        String sql = "SELECT * FROM User WHERE uuid_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
    }
//
////    public int updateUser(User user) {
////        String sql = "UPDATE User SET name = ?, email = ? , username=?, password=? WHERE id = ?";
////        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getId());
////    }
//
    public int deleteUser(String id) {
        String sql = "DELETE FROM User WHERE uuid_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
