package com.example.LearningRestAPIs.LearningRestAPIs.service.impl;

import com.example.LearningRestAPIs.LearningRestAPIs.dto.AddStudentRequestDto;
import com.example.LearningRestAPIs.LearningRestAPIs.dto.StudentDto;
import com.example.LearningRestAPIs.LearningRestAPIs.entity.Student;
import com.example.LearningRestAPIs.LearningRestAPIs.repository.StudentRepository;
import com.example.LearningRestAPIs.LearningRestAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();

//        List<StudentDto> studentDtoList = new ArrayList<>();
//        for (Student student : studentList) {
//            StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getEmail());
//            studentDtoList.add(studentDto);
//        }

        List<StudentDto> studentDtoList;
        studentDtoList = studentList.stream().map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail())).toList();
//        studentDtoList = studentList.stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();

        return studentDtoList;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with ID: "+id));
//        return new StudentDto(student.getId(), student.getName(), student.getEmail());
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist by id: "+id);
        };
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudentById(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with ID: "+id));
        modelMapper.map(addStudentRequestDto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with ID: "+id));

        updates.forEach((field, value)-> {
            switch (field){
                case "name": student.setName((String) value);
                break;
                case "email": student.setEmail((String) value);
                break;
                default: throw new IllegalArgumentException("Field is not supported");
            }
        });

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
