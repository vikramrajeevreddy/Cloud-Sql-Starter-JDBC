package com.elcarim.cloudsqlstarter.controller;

import com.elcarim.cloudsqlstarter.model.User;
import com.elcarim.cloudsqlstarter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/users")
public class UserController {

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/getUsers")
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
//
//        @PostMapping("/addUser")
//        public ResponseEntity<User> addUser(@RequestBody User user) {
//            userService.addUser(user);
//            return new ResponseEntity<>(user, HttpStatus.CREATED);
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
//            user.setId(id);  // Ensure the user's ID is set to the path variable 'id'
//            userService.updateUser(user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
//            userService.deleteUser(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
    }
