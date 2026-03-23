package com.girija.survay_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.girija.survay_service.service.SurveyService;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;



    @GetMapping("/status/{empId}")
    public boolean checkStatus(@PathVariable String empId) {
        return surveyService.checkSurveySubmitted(empId);
    }

    @PostMapping("/mark-submitted")
    public String markSubmitted(@RequestBody Map<String, String> body) {
        String empId = body.get("empId");
        return surveyService.markSurveySubmitted(empId);

    }

    // New endpoint to set survey status (submitted or not)
    @PostMapping("/mark-status")
    public String markStatus(@RequestBody Map<String, Object> body) {
        String empId = (String) body.get("empId");
        boolean submitted = Boolean.TRUE.equals(body.get("submitted"));
        return surveyService.markSurveyStatus(empId, submitted);
    }

}
