package com.example.EmployeeApiLearning.services.impl;

import com.example.EmployeeApiLearning.dto.AddEmployeeRequestDto;
import com.example.EmployeeApiLearning.dto.EmployeeDto;
import com.example.EmployeeApiLearning.entity.Employee;
import com.example.EmployeeApiLearning.repository.EmployeeRepository;
import com.example.EmployeeApiLearning.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> getAllEmployee() {
        final List<Employee> allEmployees = employeeRepository.findAll();
        final List<EmployeeDto> EmployeeDtoList;
        EmployeeDtoList = allEmployees.stream().map((element)-> new EmployeeDto(element.getId(), element.getName(), element.getEmail())).toList();
        return EmployeeDtoList;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Employee not found with Id+ "+id));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto createNewEmployee(AddEmployeeRequestDto addEmployeeRequestDto) {
        Employee newEmployee  = modelMapper.map(addEmployeeRequestDto, Employee.class);
        Employee employee = employeeRepository.save(newEmployee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if(!employeeRepository.existsById(id)){
            throw new IllegalArgumentException("Employee not found by Id: "+id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto updateEmployeeById(Long id, AddEmployeeRequestDto addEmployeeRequestDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Employee Doesn't exist by id: "+id));
        modelMapper.map(addEmployeeRequestDto, employee);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updatePartialEmployee(Long id, Map<String, Object> updates) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Employee doesnt exist by id: "+ id));
        updates.forEach((field, value)->{
            switch (field){
                case "name":employee.setName((String) value);
                break;
                case "email":employee.setEmail((String)value);
                break;
                case "password":employee.setPassword((String)value);
                break;
                default:throw new IllegalArgumentException("Field is not supported");
            }
        });

        employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
