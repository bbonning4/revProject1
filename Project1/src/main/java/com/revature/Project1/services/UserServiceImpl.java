package com.revature.Project1.services;

import com.revature.Project1.models.Cat;
import com.revature.Project1.models.User;
import com.revature.Project1.repositories.CatRepo;
import com.revature.Project1.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo ur;
    @Autowired
    CatRepo cr;

    @Override
    public User signup(User u) {
        return ur.save(u);
    }

    @Override
    public Optional<User> login(User u) {
        return ur.findByUsernameAndPassword(u.getUsername(), u.getPassword());
    }

    @Override
    public List<Cat> getUserCats(int user_id) {
        return cr.findByOwnerId(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return ur.findAll();
    }

    @Override
    public Optional<User> getUser(int user_id) {
        return ur.findById(user_id);
    }
}
