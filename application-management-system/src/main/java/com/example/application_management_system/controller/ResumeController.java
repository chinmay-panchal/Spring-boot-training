package com.example.application_management_system.controller;

import com.example.application_management_system.entity.Resume;
import com.example.application_management_system.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @PostMapping("/{applicantId}/resume")
    public ResponseEntity<Resume> addResume(@PathVariable Long applicantId, @RequestBody Resume resume){
        return ResponseEntity.ok(resumeService.addResume(applicantId, resume));
    }

    @GetMapping("/resume")
    public ResponseEntity<List<Resume>> getAllResume(){
        return ResponseEntity.ok(resumeService.getAllResume());
    }
}
