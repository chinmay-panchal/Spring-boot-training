package com.example.LearningRestAPIs.LearningRestAPIs.service;

import com.example.LearningRestAPIs.LearningRestAPIs.dto.AddStudentRequestDto;
import com.example.LearningRestAPIs.LearningRestAPIs.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentDto updateStudentById(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
