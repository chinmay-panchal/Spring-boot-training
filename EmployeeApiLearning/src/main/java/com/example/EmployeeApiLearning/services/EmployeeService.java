package com.example.EmployeeApiLearning.services;

import com.example.EmployeeApiLearning.dto.AddEmployeeRequestDto;
import com.example.EmployeeApiLearning.dto.EmployeeDto;
import com.example.EmployeeApiLearning.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployee();
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createNewEmployee(AddEmployeeRequestDto addEmployeeRequestDto);
    void deleteEmployeeById(Long id);
    EmployeeDto updateEmployeeById(Long id, AddEmployeeRequestDto addEmployeeRequestDto);
    EmployeeDto updatePartialEmployee(Long id, Map<String, Object> updates);
}
