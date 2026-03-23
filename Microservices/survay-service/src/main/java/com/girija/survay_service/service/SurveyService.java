package com.girija.survay_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.girija.survay_service.entity.SurveyResponse;
import com.girija.survay_service.entity.SurveyStatus;
import com.girija.survay_service.repository.SurveyResponseRepository;
import com.girija.survay_service.repository.SurveyStatusRepository;

@Service
public class SurveyService {

    
@Autowired
    private SurveyStatusRepository statusRepo;

    @Autowired
    private SurveyResponseRepository responseRepo;

    @Autowired
    private ObjectMapper objectMapper;  // Jackson default mapper

    public String submitSurvey(String empId, Object answers) {

        String jsonAnswer;
        try {
            jsonAnswer = objectMapper.writeValueAsString(answers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse survey answers into JSON");
        }

        SurveyResponse response = new SurveyResponse();
        response.setEmpId(empId);
        response.setAnswer(jsonAnswer);
        response.setSubmittedAt(LocalDateTime.now());

        SurveyStatus status = new SurveyStatus();
        status.setEmpId(empId);
        status.setSubmitted(true);
        status.setSubmittedAt(LocalDateTime.now());

        statusRepo.save(status);

        return "Survey submitted successfully";
    }

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
