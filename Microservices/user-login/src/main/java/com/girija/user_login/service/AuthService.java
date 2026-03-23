package com.girija.user_login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.girija.user_login.entity.UserAuth;
import com.girija.user_login.exception.UserNotFoundException;
import com.girija.user_login.repository.UserAuthRepository;

@Service
public class AuthService {

    @Autowired
    private UserAuthRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${survey.service.url:http://localhost:5000}")
    private String surveyServiceUrl;

    public String login(String empId, String password) {

        UserAuth user = repo.findById(empId)
                .orElseThrow(() -> new UserNotFoundException("Invalid User ID"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException("Invalid Password");
        }

        if (user.isFirstLogin()) {
            return "FIRST_LOGIN";
        }

        // Call survey-service to set status as not submitted
        try {
            String url = surveyServiceUrl + "/survey/mark-status";
            Map<String, Object> req = new HashMap<>();
            req.put("empId", empId);
            req.put("submitted", false);
            restTemplate.postForObject(url, req, String.class);
        } catch (Exception e) {
            // Log the error but do not fail login if survey service is down
            System.err.println("Failed to update survey status for user " + empId + ": " + e.getMessage());
        }
        return "LOGIN_SUCCESS";
    }

}
