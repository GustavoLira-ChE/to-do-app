package com.thdtraining.todoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thdtraining.todoserver.models.User;
import com.thdtraining.todoserver.pojos.UserPojos;
import com.thdtraining.todoserver.pojos.UserShowPojo;
import com.thdtraining.todoserver.repository.UserRepository;

@Service
public class UserService implements UserServiceImp {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUser() {
        return this.userRepo.findAll();
    }

    @Override
    public UserShowPojo findUserById(Integer id_user) {
        User userAllInfo = this.userRepo.findById(id_user).orElse(null);
        UserShowPojo userShow = new UserShowPojo();
        userShow.setIduser(userAllInfo.getIduser());
        userShow.setFirstName(userAllInfo.getFirstName());
        userShow.setLastName(userAllInfo.getLastName());
        userShow.setEmail(userAllInfo.getEmail());
        userShow.setRole(userAllInfo.getRole()); 
        return userShow;
    }

    @Override
    public User createUser(UserPojos newUser) {
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole("REGULAR");
        return this.userRepo.save(user);
    }
    
    @Override
    public boolean deleteUserById(Integer id) {
        if(this.userRepo.existsById(id)){
            this.userRepo.deleteById(id);
            return true;
        }
        return false; 
    }

    @Override
    public UserShowPojo findUserByEmail(String email) {
        User userAllInfo = this.userRepo.findOneByEmail(email);
        UserShowPojo userShow = new UserShowPojo();
        userShow.setIduser(userAllInfo.getIduser());
        userShow.setFirstName(userAllInfo.getFirstName());
        userShow.setLastName(userAllInfo.getLastName());
        userShow.setEmail(userAllInfo.getEmail());
        userShow.setRole(userAllInfo.getRole()); 
        return userShow; 
    }
}
