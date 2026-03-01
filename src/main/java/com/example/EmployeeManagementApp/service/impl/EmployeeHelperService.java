package com.example.EmployeeManagementApp.service.impl;

import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.request.EmployeeQualificationDTO;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.entity.Employee;
import com.example.EmployeeManagementApp.entity.EmployeeQualification;
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

    public Employee mapToEntity(EmployeeDTO dto) {
        Employee employee = Employee.builder()
                .name(dto.getName())
                .skills(dto.getSkills())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .department(dto.getDepartment())
                .experience(dto.getExperience())
                .salary(dto.getSalary())
                .hireDate(dto.getHireDate())
                .build();

        List<EmployeeQualification> qualifications = dto.getQualifications().stream()
                .map(qDto -> EmployeeQualification.builder()
                        .qualification(qDto.getQualification())
                        .institution(qDto.getInstitution())
                        .year(qDto.getYear())
                        .employee(employee) // Link child to parent
                        .build())
                .toList();

        employee.setQualifications(qualifications);
        return employee;
    }


}
