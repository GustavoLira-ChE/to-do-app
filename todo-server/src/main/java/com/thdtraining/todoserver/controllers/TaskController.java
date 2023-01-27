package com.thdtraining.todoserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thdtraining.todoserver.models.Task;
import com.thdtraining.todoserver.pojos.CompletedChange;
import com.thdtraining.todoserver.pojos.TaskPojos;
import com.thdtraining.todoserver.services.TaskService;



@RestController
@RequestMapping("/task")
public class TaskController {
    
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/user-id/{id}")
    public List<Task> getTaskByUserId(@PathVariable(name="id") int iduser){
        return this.taskService.findAllTaskByUserId(iduser);
    }

    @GetMapping("/task-id/{id}")
    public Task getTaskById(@PathVariable(name="id") int id){
        return this.taskService.findTaskById(id);
    }

    @PostMapping()
    public Task createTask(@RequestBody TaskPojos task){
        return this.taskService.createTask(task);
    }

    @DeleteMapping("/task-id/{id}")
    public boolean deleteTaskById(@PathVariable(name="id") int id){
        return this.deleteTaskById(id);
    }

    @PutMapping("/task-id/{id}")
    public boolean updateTaskState(@RequestBody CompletedChange info, @PathVariable(name="id") int id){
        return this.taskService.updateCompleted(info, id);
    }

}
