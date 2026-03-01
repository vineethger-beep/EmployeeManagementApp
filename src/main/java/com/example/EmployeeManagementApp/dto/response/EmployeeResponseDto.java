package com.example.EmployeeManagementApp.dto.response;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeQualificationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Department department;
    private Double salary;
    private LocalDate hireDate;
    private Integer experience;
    private List<String> skills;

    // The "Many" part of the relationship
    private List<EmployeeQualificationDTO> qualifications;
}
