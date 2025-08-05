package com.example.EmployeeApiLearning.controller;

import com.example.EmployeeApiLearning.dto.AddEmployeeRequestDto;
import com.example.EmployeeApiLearning.dto.EmployeeDto;
import com.example.EmployeeApiLearning.entity.Employee;
import com.example.EmployeeApiLearning.services.EmployeeService;
import com.sun.net.httpserver.HttpServer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody @Valid AddEmployeeRequestDto addEmployeeRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createNewEmployee(addEmployeeRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody @Valid AddEmployeeRequestDto addEmployeeRequestDto){
        return ResponseEntity.ok(employeeService.updateEmployeeById(id, addEmployeeRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> updatePartialEmployee(@PathVariable Long id, @RequestBody Map<String, Object>updates){
        return ResponseEntity.ok(employeeService.updatePartialEmployee(id,updates ));
    }
}