package com.girija.adminpanel.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    private Long associateId;

    private String associateName;
    private Long homeManagerId;
    private String homeManagerName;
    private String homeManagerGrade;
    private String fteOrCwr;
    private String activeOrExits;

    // YYYY-MM format → stored as LocalDate (1st of month)
    private LocalDate yearAndMonthOfJoining;

    private String grade;
    private String serviceLine;
    private String vertical;
    private String onsiteOrOffshore;
    private String region;
    private String location;

    private Long projectId;
    private String projectName;

    private Long accountId;
    private String accountName;

    private Long parentCustId;
    private String parentCustName;
}
