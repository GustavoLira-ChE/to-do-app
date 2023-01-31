package com.thdtraining.todoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thdtraining.todoserver.models.User;
import com.thdtraining.todoserver.pojos.UserPojos;
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
    public User findUserById(Integer id_user) {
        return this.userRepo.findById(id_user).orElse(null);
    }

    @Override
    public User createUser(UserPojos newUser) {
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getFirstName());
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
    public User findUserByEmail(String email) {
        return this.userRepo.findOneByEmail(email); 
    }
}
