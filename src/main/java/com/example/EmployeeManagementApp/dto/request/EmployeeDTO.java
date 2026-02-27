package com.example.EmployeeManagementApp.dto.request;

import com.example.EmployeeManagementApp.constants.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;

    private Department department;
    private Double salary;
    private LocalDate hireDate;
    private Integer experience;

    private List<QualificationDTO> qualifications;
    private List<String> skills;
}
