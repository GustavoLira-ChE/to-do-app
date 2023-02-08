package com.thdtraining.todoserver.services;

import java.util.List;

import com.thdtraining.todoserver.models.User;
import com.thdtraining.todoserver.pojos.UserPojos;
import com.thdtraining.todoserver.pojos.UserShowPojo;

public interface UserServiceImp {

    public List<User> findAllUser();
    public UserShowPojo findUserById(Integer id_user);
    public User createUser(UserPojos newUser);
    public boolean deleteUserById(Integer id);
    public UserShowPojo findUserByEmail(String email);

}
