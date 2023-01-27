package com.thdtraining.todoserver.services;

import java.util.List;

import com.thdtraining.todoserver.models.Task;
import com.thdtraining.todoserver.pojos.CompletedChange;
import com.thdtraining.todoserver.pojos.TaskPojos;

public interface TaskServiceImp {

    public List<Task> findAllTaskByUserId(Integer id_user);
    public Task findTaskById(Integer id_task);
    public Task createTask(TaskPojos newTask);
    public boolean deleteTaskById(Integer id);
    public boolean updateCompleted(CompletedChange info,int id_task);
}
