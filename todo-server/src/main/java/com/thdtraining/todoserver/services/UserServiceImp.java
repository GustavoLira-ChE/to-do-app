package com.thdtraining.todoserver.services;

import java.util.List;

import com.thdtraining.todoserver.models.User;
import com.thdtraining.todoserver.pojos.UserPojos;

public interface UserServiceImp {

    public List<User> findAllUser();
    public User findUserById(Integer id_user);
    public User createUser(UserPojos newUser);
    public boolean deleteUserById(Integer id);

}
