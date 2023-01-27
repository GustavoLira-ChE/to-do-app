package com.thdtraining.todoserver.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskPojos {

    private String name;
    private String description;
    private int iduser;
    
}
