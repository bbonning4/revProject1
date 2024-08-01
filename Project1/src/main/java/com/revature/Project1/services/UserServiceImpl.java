package com.revature.Project1.services;

import com.revature.Project1.models.User;
import com.revature.Project1.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo ur;

    @Override
    public User signup(User u) {
        return ur.save(u);
    }
}
