package com.girija.survay_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.girija.survay_service.entity.SurveyStatus;
import com.girija.survay_service.repository.SurveyStatusRepository;

@Service
public class SurveyService {

    @Autowired
    private SurveyStatusRepository statusRepo;

    public boolean checkSurveySubmitted(String empId) {
        return statusRepo.findById(empId)
                .map(SurveyStatus::isSubmitted)
                .orElse(false);
    }

    public String markSurveySubmitted(String empId) {
        SurveyStatus status = new SurveyStatus();
        status.setEmpId(empId);
        status.setSubmitted(true);
        status.setSubmittedAt(LocalDateTime.now());
        statusRepo.save(status);
        return "Survey marked as submitted";
    }

    // New method to set survey status (submitted or not)
    public String markSurveyStatus(String empId, boolean submitted) {
        SurveyStatus status = new SurveyStatus();
        status.setEmpId(empId);
        status.setSubmitted(submitted);
        if (submitted) {
            status.setSubmittedAt(LocalDateTime.now());
        } else {
            status.setSubmittedAt(null);
        }
        statusRepo.save(status);
        return submitted ? "Survey marked as submitted" : "Survey marked as not submitted";
    }
}
