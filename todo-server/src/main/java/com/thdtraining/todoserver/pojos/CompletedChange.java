package com.thdtraining.todoserver.pojos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompletedChange {
    
    private LocalDate last_updated;
    private boolean completed;
}
