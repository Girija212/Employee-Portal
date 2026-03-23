package com.girija.survay_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.girija.survay_service.entity.SurveyStatus;
import com.girija.survay_service.repository.SurveyStatusRepository;

@Service
public class ReminderScheduler {

    

    @Autowired
    private SurveyStatusRepository statusRepo;

    @Autowired
    private JavaMailSender mailSender;

    // Runs daily at 10 AM
    @Scheduled(cron = "0 0 10 * * *")
    public void sendReminders() {

        List<SurveyStatus> pending = statusRepo.findBySubmittedFalse();

        for (SurveyStatus status : pending) {

            String email = status.getEmpId() + "@cognizant.com";

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(email);
            msg.setSubject("Survey Reminder");
            msg.setText("You have not submitted your survey yet. Please complete it.");

            mailSender.send(msg);
        }
    }

}
