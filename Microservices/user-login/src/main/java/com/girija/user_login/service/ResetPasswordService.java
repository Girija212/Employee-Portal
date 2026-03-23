package com.girija.user_login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.girija.user_login.entity.UserAuth;
import com.girija.user_login.exception.UserNotFoundException;
import com.girija.user_login.repository.UserAuthRepository;

@Service
public class ResetPasswordService {

    
@Autowired
    private UserAuthRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String resetPassword(String empId, String newPassword) {

    UserAuth user = repo.findById(empId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

    user.setPassword(encoder.encode(newPassword));
    user.setFirstLogin(false);

    repo.save(user);

    return "Password updated successfully";
}

}
