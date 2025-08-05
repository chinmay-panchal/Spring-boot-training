package com.example.application_management_system.service;

import com.example.application_management_system.entity.Applicant;
import com.example.application_management_system.entity.Job;
import com.example.application_management_system.repository.ApplicantJpaRepository;
import com.example.application_management_system.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    private ApplicantJpaRepository applicantJpaRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job createJob(Job job){
        return jobRepository.save(job);
    }

    public Applicant addJobToApplicant(Long applicantId, Long jobId){
        Optional<Applicant> applicant = applicantJpaRepository.findById(applicantId);
        Optional<Job>job = jobRepository.findById(jobId);

        if(applicant.isPresent() && job.isPresent()){
            applicant.get().getJobs().add(job.get());
            applicantJpaRepository.save(applicant.get());

//            job.get().getApplicants().add(applicant.get());
//            jobRepository.save(job.get());
            return applicant.get();

        }else {
            throw new IllegalArgumentException("Applicant or Job not found");
        }
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + id));
    }
}
