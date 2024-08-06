package com.revature.Project1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.Project1.models.Cat;
import com.revature.Project1.models.User;
import com.revature.Project1.services.CatService;
import com.revature.Project1.services.UserService;
import com.revature.Project1.util.TokenStore;
import com.revature.Project1.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    UserService us;

    @Autowired
    public UserController(UserService us, CatService cs) {
        this.us = us;
    }

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private TokenStore tokenStore;

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User u) {
        u = us.signup(u);
        // todo: handle edge cases such as password length, etc.
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User u) {
        if (us.login(u).isPresent()) {
            String token = tokenUtil.generateToken(u.getUsername());
            tokenStore.storeToken(u.getUsername(), token);

            Map<String, String> response = new HashMap<>();
            response.put("message", "login successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid credentials");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestParam String username) {
        // todo: only allow logout by the login user
        tokenStore.removeToken(username);
        Map<String, String> response = new HashMap<>();
        response.put("message", "logout successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestParam String username) {
        String token = tokenStore.getToken(username);
        boolean isValid = token != null && tokenStore.validateToken(username, token);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isValid", isValid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/cats")
    public ResponseEntity<Object> getUserCats(@PathVariable int userId) {
        // todo: only allow login User to access their cats
        if (us.getUser(userId).isPresent()) {
            List<Cat> cats = us.getUserCats(userId);
            return new ResponseEntity<>(cats, HttpStatus.OK);
        } else {
            String errorMessage = "User with ID " + userId + " not found.";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = us.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
