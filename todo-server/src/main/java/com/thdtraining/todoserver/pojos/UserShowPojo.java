package com.thdtraining.todoserver.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserShowPojo {
    private Integer iduser; 
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
