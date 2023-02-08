package com.thdtraining.todoserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thdtraining.todoserver.models.User;
//import com.thdtraining.todoserver.pojos.UserEmail;
import com.thdtraining.todoserver.pojos.UserPojos;
import com.thdtraining.todoserver.pojos.UserShowPojo;
import com.thdtraining.todoserver.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200/*")
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
    public UserShowPojo getUserById(@PathVariable(name = "id") int id){
        return this.userService.findUserById(id);
    }

    @GetMapping("/user-data/{email}")
    public UserShowPojo getUserByEmail(@PathVariable(name = "email") String email){
        return this.userService.findUserByEmail(email);
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
