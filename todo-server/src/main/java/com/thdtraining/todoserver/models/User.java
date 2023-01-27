package com.thdtraining.todoserver.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_table")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iduser;

    private String firstName;
    private String lastName;
    
    @Column(unique = true)
    private String email;
    @Column(name="user_password")
    private String password;
    @Column(name="user_role")
    private String role;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy="iduser")
	private List<Task> tasks;
    
    public void addPost(Task task){
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        task.setIduser(this);
    }
}
