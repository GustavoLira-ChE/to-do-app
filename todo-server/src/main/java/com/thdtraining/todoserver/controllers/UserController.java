package com.thdtraining.todoserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thdtraining.todoserver.models.User;
import com.thdtraining.todoserver.pojos.UserPojos;
import com.thdtraining.todoserver.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAllUser(){
        return this.userService.findAllUser();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable(name = "id") int id){
        return this.userService.findUserById(id);
    }

    @PostMapping()
    public User createUser(@RequestBody UserPojos newUser){
        return this.userService.createUser(newUser);
    }
    

    @DeleteMapping("/id/{id}")
    public boolean deleteUserById(@PathVariable(name="id") int id){
        return this.userService.deleteUserById(id);
    }
}