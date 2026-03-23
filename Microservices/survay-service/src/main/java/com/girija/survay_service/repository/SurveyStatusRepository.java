package com.girija.survay_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.girija.survay_service.entity.SurveyStatus;

public interface SurveyStatusRepository extends JpaRepository<SurveyStatus, String> {

    List<SurveyStatus> findBySubmittedFalse();
}
