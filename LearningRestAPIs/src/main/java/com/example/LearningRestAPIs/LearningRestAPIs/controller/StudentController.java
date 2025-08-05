package com.example.LearningRestAPIs.LearningRestAPIs.controller;

import com.example.LearningRestAPIs.LearningRestAPIs.dto.AddStudentRequestDto;
import com.example.LearningRestAPIs.LearningRestAPIs.dto.StudentDto;
//import com.example.LearningRestAPIs.LearningRestAPIs.entity.Student;
//import com.example.LearningRestAPIs.LearningRestAPIs.repository.StudentRepository;
import com.example.LearningRestAPIs.LearningRestAPIs.service.StudentService;
//import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
//@RequiredArgsConstructor
public class StudentController {

//    private final StudentRepository studentRepository;

//    public StudentController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

//    @GetMapping("/student")
//    public List<Student> getStudent(){
//        return studentRepository.findAll();
//    }

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //    @GetMapping("/student/{id}/{name}")
//    public String getStudentsById(@PathVariable Long id, @PathVariable String name){
//        return "Path variable "+id+" name is "+name;
//    }


    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent() {
//        return studentService.getAllStudents();
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
//        return studentService.getStudentById(id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudentById(id, addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }

}
