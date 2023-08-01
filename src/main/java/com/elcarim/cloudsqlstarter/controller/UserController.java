package com.elcarim.cloudsqlstarter.controller;

import com.elcarim.cloudsqlstarter.model.User;
import com.elcarim.cloudsqlstarter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/users")
public class UserController {

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/all")
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

//        @GetMapping("/{id}")
//        public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
//            //System.out.println("uuid _ Steetetet: " +uuid_id);
//            User user = userService.getUserById(id);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }

        @GetMapping("/{uuid}")
        public ResponseEntity<User> getUserByUuid(@PathVariable("uuid") String uuid) {
            //System.out.println("uuid _ Steetetet: " +uuid_id);
            User user = userService.getUserByUuid(uuid);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }


        @PostMapping("/addUser")
        public ResponseEntity<String> addUser(@RequestBody User user) {
            UUID uuid = UUID.randomUUID();
            System.out.println("randome uuid: " + uuid);
            user.setUuid_id(uuid.toString());
            System.out.println("userrr: " + user);
            userService.addUser(user);
            return new ResponseEntity<>(user.getUuid_id(), HttpStatus.CREATED);
        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
//            user.setId(id);  // Ensure the user's ID is set to the path variable 'id'
//            userService.updateUser(user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//
        @DeleteMapping("/{uuid}")
        public ResponseEntity<?> deleteUser(@PathVariable("uuid") String uuid) {
            userService.deleteUser(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
