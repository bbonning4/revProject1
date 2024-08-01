package com.revature.Project1.controllers;

import com.revature.Project1.models.User;
import com.revature.Project1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    UserService us;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User u) {
        System.out.println(u.toString());
        u = us.signup(u);
        // todo: handle edge cases such as username taken
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
