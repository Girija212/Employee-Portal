package com.girija.user_login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserAuth {

    
    @Id
    private String empId;

    private String password;

    private boolean firstLogin = true;

    private String resetToken;

}
