package com.thdtraining.todoserver.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojos {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

}
