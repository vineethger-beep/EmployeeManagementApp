package com.example.EmployeeManagementApp.service.impl;

import com.example.EmployeeManagementApp.dto.request.EmployeeQualificationDTO;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeHelperService {

    public EmployeeResponseDto mapToResponseDto(Employee entity) {
        return EmployeeResponseDto.builder()
                .id(entity.getId())
                .skills(entity.getSkills())
                .phone(entity.getPhone())
                .salary(entity.getSalary())
                .hireDate(entity.getHireDate())
                .experience(entity.getExperience())
                .name(entity.getName())
                .email(entity.getEmail())
                .department(entity.getDepartment())
                .qualifications(entity.getQualifications().stream()
                        .map(q -> EmployeeQualificationDTO.builder()
                                .qualification(q.getQualification())
                                .institution(q.getInstitution())
                                .year(q.getYear())
                                .build())
                        .toList())
                .build();
    }

    public List<EmployeeResponseDto> getEmployeeResponseDtos(List<Employee> savedEmployees) {
        return savedEmployees.stream().map(emp -> {
            return getResponseDtoFromEntity(emp);
        }).toList();
    }

    public EmployeeResponseDto getResponseDtoFromEntity(Employee emp) {
        List<EmployeeQualificationDTO> qDtos = emp.getQualifications().stream()
                .map(q -> EmployeeQualificationDTO.builder()
                        .qualification(q.getQualification())
                        .year(q.getYear())
                        .institution(q.getInstitution())
                        .id(q.getId())
                        .build())
                .toList();
        return EmployeeResponseDto.builder()
                .id(emp.getId())
                .name(emp.getName())
                .department(emp.getDepartment())
                .email(emp.getEmail())
                .qualifications(qDtos)
                .phone(emp.getPhone())
                .salary(emp.getSalary())
                .hireDate(emp.getHireDate())
                .experience(emp.getExperience())
                .skills(emp.getSkills())
                .build();
    }


}
