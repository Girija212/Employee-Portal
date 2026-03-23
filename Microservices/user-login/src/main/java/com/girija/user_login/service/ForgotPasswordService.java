package com.girija.user_login.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.girija.user_login.entity.UserAuth;

import com.girija.user_login.exception.UserNotFoundException;
import com.girija.user_login.repository.UserAuthRepository;



@Service
public class ForgotPasswordService {

    @Autowired
    private UserAuthRepository authRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String forgotPassword(String empId) {

        // 1️⃣ CALL ADMIN-PANEL SERVICE USING REST TEMPLATE
        String url = "http://localhost:3000/admin/employee/exists/" + empId;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "password"); // admin-panel credentials here

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);

        Boolean exists = response.getBody();

        if (!Boolean.TRUE.equals(exists)) {
            throw new UserNotFoundException("Employee ID not found in Excel data");
        }

        // 2️⃣ AUTO CREATE USER-AUTH ENTRY
        UserAuth user = authRepo.findById(empId).orElse(null);

        if (user == null) {
            user = new UserAuth();
            user.setEmpId(empId);
        }

        // 3️⃣ GENERATE DEFAULT PASSWORD
        String defaultPassword = generateDefaultPassword();
        user.setPassword(encoder.encode(defaultPassword));
        user.setFirstLogin(true);

        authRepo.save(user);

        // 4️⃣ SEND EMAIL
        String email = empId + "@cognizant.com";

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Your Default Password");
        msg.setText("Hello,\n\nYour default password is: " 
                + defaultPassword + "\nPlease login and reset your password.\n");

        mailSender.send(msg);
        //System.out.println("Default password sent to: " + email);

        return "Default password sent to: " + email;
    }

    private String generateDefaultPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
