package com.example.EmployeeManagementApp.service;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.response.BatchProcessResponse;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public BatchProcessResponse saveEmployees(List<EmployeeDTO> employees);

    List<EmployeeResponseDto> findWithFilters(Department department, Integer experience, List<String> skill);

    Employee update(Long id, Employee details);

    void delete(Long id);

    Employee findById(Long id);

    void deleteEmployee(Long id);

//    EmployeeResponseDto patchEmployee(Long id, com.example.EmployeeManagementApp.dto.EmployeePatchDto dto);
}
