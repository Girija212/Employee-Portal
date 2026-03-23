package com.girija.user_login.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.girija.user_login.service.ResetPasswordService;

@RestController
@RequestMapping("/auth")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetService;

    @PostMapping("/reset")
    public String reset(@RequestBody Map<String, String> body) {

        String empId = body.get("empId");
        String newPassword = body.get("newPassword");

        return resetService.resetPassword(empId, newPassword);
    }
}
