package com.example.EmployeeManagementApp.service.impl;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.request.EmployeeQualificationDTO;
import com.example.EmployeeManagementApp.dto.response.BatchProcessResponse;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.dto.response.FailedRecordDetails;
import com.example.EmployeeManagementApp.entity.Employee;
import com.example.EmployeeManagementApp.entity.EmployeeQualification;
import com.example.EmployeeManagementApp.repository.EmployeeRepository;
import com.example.EmployeeManagementApp.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeHelperService employeeHelperService;



    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Override
    public BatchProcessResponse saveEmployees(List<EmployeeDTO> dtos) {
        List<EmployeeResponseDto> successfulRecords = new ArrayList<>();
        List<FailedRecordDetails> failedRecords = new ArrayList<>();

        // process as chunks with size 50
        for (int i = 0; i < dtos.size(); i += batchSize) {
            List<EmployeeDTO> chunk = dtos.subList(i, Math.min(i + batchSize, dtos.size()));

            try {
                // Try to save the whole chunk at once for speed
                List<EmployeeResponseDto> savedChunk = saveChunkTransactional(chunk);
                successfulRecords.addAll(savedChunk);
            } catch (Exception e) {
                // If chunk fails, process individually to catch the specific bad DTO
                processIndividualErrors(chunk, successfulRecords, failedRecords);
            }
        }

        return BatchProcessResponse.builder()
                .totalRecords(dtos.size())
                .successCount(successfulRecords.size())
                .failureCount(failedRecords.size())
                .successfulRecords(successfulRecords)
                .failedRecords(failedRecords)
                .build();
    }

    @Transactional
    public List<EmployeeResponseDto> saveChunkTransactional(List<EmployeeDTO> chunk) {
        List<Employee> entities = chunk.stream().map(this::mapToEntity).toList();
        List<Employee> saved = employeeRepository.saveAll(entities);
        return saved.stream().map(employeeHelperService::mapToResponseDto).toList();
    }

    private void processIndividualErrors(List<EmployeeDTO> chunk,
                                         List<EmployeeResponseDto> successList,
                                         List<FailedRecordDetails> failList) {
        for (EmployeeDTO dto : chunk) {
            try {
                // Individual save to isolate the error
                Employee saved = employeeRepository.save(mapToEntity(dto));
                successList.add(employeeHelperService.mapToResponseDto(saved));
            } catch (Exception e) {
                failList.add(new FailedRecordDetails(dto, e.getMessage()));
            }
        }
    }
    private Employee mapToEntity(EmployeeDTO dto) {
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



    private List<EmployeeResponseDto> getEmployeeResponseDtos(List<Employee> savedEmployees) {
        return savedEmployees.stream().map(emp -> {
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
        }).toList();
    }

    @Override
    public Page<EmployeeResponseDto> findWithFilters(
            Department department,
            Integer experience,
            List<String> skills,
            Pageable pageable) {

        if (skills == null || skills.isEmpty()) {
            skills = null;
        }

        Page<Employee> employeePage =
                employeeRepository.findWithFilters(department, experience, skills, pageable);

        return employeePage.map(employeeHelperService::getResponseDtoFromEntity);
    }

    private String normalize(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }

    @Override
    public Employee update(Long id, Employee details) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        employeeRepository.deleteById(id);
    }


    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeDTO dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Simple fields
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(dto.getHireDate());
        employee.setExperience(dto.getExperience());
        employee.setSkills(dto.getSkills());

        // Replace qualifications safely
        employee.getQualifications().clear();

        if (dto.getQualifications() != null) {
            for (EmployeeQualificationDTO qdto : dto.getQualifications()) {

                EmployeeQualification qualification = new EmployeeQualification();

                qualification.setQualification(qdto.getQualification());
                qualification.setInstitution(qdto.getInstitution());
                qualification.setYear(qdto.getYear());

                qualification.setEmployee(employee); // VERY IMPORTANT

                employee.getQualifications().add(qualification);
            }
        }

        return employeeHelperService.getResponseDtoFromEntity(employee);
    }

}
