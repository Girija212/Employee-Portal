package com.girija.survay_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.girija.survay_service.entity.SurveyResponse;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {

}
