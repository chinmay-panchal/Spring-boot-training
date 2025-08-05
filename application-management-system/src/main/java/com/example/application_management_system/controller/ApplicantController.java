package com.example.application_management_system.controller;

import com.example.application_management_system.entity.Applicant;
import com.example.application_management_system.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping
    public List<Applicant> getAllApplicants(){
        return applicantService.getAllApplicants();
    }

    @PostMapping
    public Applicant saveApplicant(@RequestBody Applicant applicant){
        return applicantService.saveApplicantCrud(applicant);
    }

    @GetMapping("/page")
    public Iterable<Applicant> getApplicantWithPagination(@RequestParam int page, @RequestParam int size){
        return applicantService.getApplicantWithPagination(page, size);
    }

    @GetMapping("/getByStatus")
    public List<Applicant> getApplicantByStatus(@RequestParam String status){
        return applicantService.getApplicantByStatus(status);
    }

    @GetMapping("/getByName")
    public List<Applicant> getApplicantByPartialName(@RequestParam String name){
        return applicantService.getApplicantByPartialName(name);
    }
}
