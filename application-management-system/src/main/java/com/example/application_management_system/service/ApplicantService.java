package com.example.application_management_system.service;

import com.example.application_management_system.entity.Applicant;
import com.example.application_management_system.entity.Application;
import com.example.application_management_system.entity.Resume;
import com.example.application_management_system.repository.ApplicantJpaRepository;
import com.example.application_management_system.repository.ApplicationCrudRepository;
import com.example.application_management_system.repository.ApplicationPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    private ApplicationCrudRepository applicationCrudRepository ;

    @Autowired
    private ApplicationPagingAndSortingRepository applicationPagingAndSortingRepository;

    @Autowired
    private ApplicantJpaRepository applicantJpaRepository;

//    public ApplicantService(ApplicationCrudRepository applicationCrudRepository) {
//        this.applicationCrudRepository = applicationCrudRepository;
//    }

    public List<Applicant> getAllApplicants(){
        Iterable<Applicant> all = applicationCrudRepository.findAll();
        List<Applicant> applicantList = new ArrayList<>();
        all.forEach(applicantList::add);
        return applicantList;
    }

    public Applicant saveApplicantCrud(Applicant applicant){
        Resume resume = applicant.getResume();
        List<Application> applications = applicant.getApplications();
        if(resume!=null) {
            resume.setApplicant(applicant);
        }
        if(applications != null){
            for(Application application : applications){
                application.setApplicant(applicant);
            }
        }
        return applicationCrudRepository.save(applicant);
    }

    public Iterable<Applicant> getApplicantWithPagination(int page, int size){
        return applicationPagingAndSortingRepository.findAll(PageRequest.of(page, size));
    }

    public List<Applicant> getApplicantByStatus(String status){
        return applicantJpaRepository.findByStatusOrderByNameDesc(status);
    }

    public List<Applicant> getApplicantByPartialName(String name){
        return applicantJpaRepository.findApplicantsByPartialNameOrderByNameAsc(name);
    }

}
