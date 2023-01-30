package com.thdtraining.todoserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thdtraining.todoserver.models.User;
import com.thdtraining.todoserver.repository.UserRepository;


/* 11. Create a UserDetailService implementacion for bringing the user database information to check for users */
@Service
public class UserDetailServiceImp implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserDetailServiceImp(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = this.userRepo.findOneByEmail(username);
            /* In this point create UserDetailsImp */
            return new UserDetailsImp(user);
        } catch(UsernameNotFoundException use){
            throw new UsernameNotFoundException("The user's email: " + username + "does not exists");
        }
    }
    
}
