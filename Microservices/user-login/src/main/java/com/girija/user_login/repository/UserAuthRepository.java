package com.girija.user_login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.girija.user_login.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, String> {

    UserAuth findByResetToken(String resetToken);
}
