package com.girija.user_login.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.girija.user_login.service.AuthService;
import com.girija.user_login.service.ForgotPasswordService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ForgotPasswordService forgotService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) {
        String empId = body.get("empId");
        String password = body.get("password");
        return authService.login(empId, password);
    }

    @PostMapping("/forgot")
    public String forgot(@RequestBody Map<String, String> body) {
        String empId = body.get("empId");
        return forgotService.forgotPassword(empId);
    }

}
