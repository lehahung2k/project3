package com.hungle.mbf.learnspring.controller;

import com.hungle.mbf.learnspring.entities.Users;
import com.hungle.mbf.learnspring.service.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/public/users")
//localhost:1103/api/public/users
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public List<Users> findAllUsers() {
        return userService.getListUsers();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Users> findUserById(@PathVariable Integer uid) {
        try {
            logger.info("PARAM: " + uid);
            Users users = userService.getUserById(uid);
            return new ResponseEntity<Users>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public void addUser(@RequestBody Users user) {
        userService.saveUser(user);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable Integer uid) {
        try {
            logger.info("BODY: " + user.toString());
            Users ExistingUser = userService.getUserById(uid);
            userService.saveUser(user);
            return new ResponseEntity<Users>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Users> deleteUser(@PathVariable Integer uid) {
        try {
            Users ExistingUser = userService.getUserById(uid);
            userService.deleteUser(uid);
            return new ResponseEntity<Users>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }
}
