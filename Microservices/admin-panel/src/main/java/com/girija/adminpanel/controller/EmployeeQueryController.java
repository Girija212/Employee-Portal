package com.girija.adminpanel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.girija.adminpanel.repository.EmployeeRepository;

@RestController
@RequestMapping("/admin/employee")
public class EmployeeQueryController {

    
    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/exists/{empId}")
    public boolean exists(@PathVariable Long empId) {
        return employeeRepo.existsById(empId);
    }

}
