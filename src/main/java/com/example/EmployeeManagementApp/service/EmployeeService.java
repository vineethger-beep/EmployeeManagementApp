package com.example.EmployeeManagementApp.service;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.response.BatchProcessResponse;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public BatchProcessResponse saveEmployees(List<EmployeeDTO> employees);

    Page<EmployeeResponseDto> findWithFilters(Department department, Integer experience, List<String> skill, Pageable pageable);

    Employee update(Long id, Employee details);

    void delete(Long id);

    Employee findById(Long id);

    void deleteEmployee(Long id);

    EmployeeResponseDto updateEmployee(Long id, EmployeeDTO dto);

}
