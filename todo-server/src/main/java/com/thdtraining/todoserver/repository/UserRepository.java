package com.thdtraining.todoserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thdtraining.todoserver.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
}
