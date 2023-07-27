package com.elcarim.cloudsqlstarter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
@RestController
public class UserController {
    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/getUsers")
    public List<String> getTuples() {
        return this.jdbcTemplate.queryForList("SELECT * FROM User").stream()
                .map((m) -> m.values().toString())
                .collect(Collectors.toList());
    }
}
