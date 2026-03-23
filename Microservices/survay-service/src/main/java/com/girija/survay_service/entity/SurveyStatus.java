package com.girija.survay_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SurveyStatus {
    
    @Id
    private String empId;

    private boolean submitted;

    private LocalDateTime submittedAt;

}
