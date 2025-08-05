package com.example.application_management_system.service;

import com.example.application_management_system.entity.Applicant;
import com.example.application_management_system.entity.Application;
import com.example.application_management_system.entity.Resume;
import com.example.application_management_system.repository.ApplicantJpaRepository;
import com.example.application_management_system.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicantJpaRepository applicantJpaRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application saveApplication(Long applicantId,Application application){
        Optional<Applicant> applicationOptional = applicantJpaRepository.findById(applicantId);
        if(applicationOptional.isPresent()){
            Applicant applicant = applicationOptional.get();
            application.setApplicant(applicant);
            return applicationRepository.save(application);
        }else{
            throw new RuntimeException("Application not found with id: "+applicantId);
        }
    }

    public List<Application> getAllApplications(){
        return applicationRepository.findAll();
    }
}
