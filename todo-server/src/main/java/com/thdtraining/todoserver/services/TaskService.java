package com.thdtraining.todoserver.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thdtraining.todoserver.models.Task;
import com.thdtraining.todoserver.models.User;
import com.thdtraining.todoserver.pojos.CompletedChange;
import com.thdtraining.todoserver.pojos.TaskPojos;
import com.thdtraining.todoserver.repository.TaskRepository;
import com.thdtraining.todoserver.repository.UserRepository;

@Service
public class TaskService implements TaskServiceImp {

    private TaskRepository taskRepo;
    private UserRepository userRepo;

    @Autowired
    public TaskService(TaskRepository taskRepo, UserRepository userRepo){
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<Task> findAllTaskByUserId(Integer id_user) {
        return this.taskRepo.findAllByIduser(id_user);
    }

    @Override
    public Task findTaskById(Integer id_task) {
        return this.taskRepo.findById(id_task).orElse(null);
    }

    @Override
    public Task createTask(TaskPojos newTask) {
        Task task = new Task();
        User user;
        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());
        task.setCompleted(false);
        task.setLast_updated(null);

        if(this.userRepo.existsById(newTask.getIduser())){
            user = this.userRepo.findById(newTask.getIduser()).orElse(null);
            if(user != null){
                task.setIduser(user);
            }
        }
        
        return this.taskRepo.save(task);
    }

    @Override
    public boolean deleteTaskById(Integer id) {
        if(this.taskRepo.existsById(id)){
            this.taskRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompleted(CompletedChange info, int id_task) {
        Task task;
        if(this.taskRepo.existsById(id_task)){
            task = this.taskRepo.findById(id_task).orElse(null);
            task.setCompleted(info.isCompleted());
            task.setLast_updated(LocalDate.now());
            this.taskRepo.save(task);
            return true;
        }
        return false;
    }
    
}
