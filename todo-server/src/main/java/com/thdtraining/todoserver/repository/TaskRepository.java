package com.thdtraining.todoserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thdtraining.todoserver.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    public abstract List<Task> findAllByIduser(int id);
}
