package com.example.EmployeeManagementApp.dto;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeQualificationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePatchDto {

    private String name;
    private String email;
    private String phone;
    private Department department;
    private Double salary;
    private LocalDate hireDate;
    private Integer experience;
    private List<EmployeeQualificationDTO> qualifications;
    private List<String> skills;
}
