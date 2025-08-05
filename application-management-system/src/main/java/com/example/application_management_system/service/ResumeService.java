package com.example.application_management_system.service;

import com.example.application_management_system.entity.Applicant;
import com.example.application_management_system.entity.Resume;
import com.example.application_management_system.repository.ApplicantJpaRepository;
import com.example.application_management_system.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService{
    @Autowired
    ApplicantJpaRepository applicantJpaRepository;

    @Autowired
    ResumeRepository resumeRepository;

    public Resume addResume(Long applicantId, Resume resume){
        Optional<Applicant> applicantOptional = applicantJpaRepository.findById(applicantId);
        if(applicantOptional.isPresent()){
            Applicant applicant = applicantOptional.get();
            resume.setApplicant(applicant);
//            applicant.setResume(resume); // optional but clean
            return resumeRepository.save(resume);
        }else{
            throw new RuntimeException("Application not found with id: "+applicantId);
        }
    }

    public List<Resume> getAllResume(){
        return resumeRepository.findAll();
    }

}
