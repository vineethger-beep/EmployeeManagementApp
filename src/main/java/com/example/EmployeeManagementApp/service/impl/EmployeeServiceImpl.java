package com.example.EmployeeManagementApp.service.impl;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.request.QualificationDTO;
import com.example.EmployeeManagementApp.dto.response.BatchProcessResponse;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.dto.response.FailedRecordDetails;
import com.example.EmployeeManagementApp.entity.Employee;
import com.example.EmployeeManagementApp.entity.Qualification;
import com.example.EmployeeManagementApp.repository.EmployeeRepository;
import com.example.EmployeeManagementApp.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;



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
        return saved.stream().map(this::mapToResponseDto).toList();
    }

    private void processIndividualErrors(List<EmployeeDTO> chunk,
                                         List<EmployeeResponseDto> successList,
                                         List<FailedRecordDetails> failList) {
        for (EmployeeDTO dto : chunk) {
            try {
                // Individual save to isolate the error
                Employee saved = employeeRepository.save(mapToEntity(dto));
                successList.add(mapToResponseDto(saved));
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

        List<Qualification> qualifications = dto.getQualifications().stream()
                .map(qDto -> Qualification.builder()
                        .qualification(qDto.getDegreeName())
                        .institution(qDto.getInstitution())
                        .year(qDto.getYearOfPassing())
                        .employee(employee) // Link child to parent
                        .build())
                .toList();

        employee.setQualifications(qualifications);
        return employee;
    }

    private EmployeeResponseDto mapToResponseDto(Employee entity) {
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
                        .map(q -> QualificationDTO.builder()
                                .degreeName(q.getQualification())
                                .institution(q.getInstitution())
                                .yearOfPassing(q.getYear())
                                .build())
                        .toList())
                .build();
    }

    private List<EmployeeResponseDto> getEmployeeResponseDtos(List<Employee> savedEmployees) {
        return savedEmployees.stream().map(emp -> {
            List<QualificationDTO> qDtos = emp.getQualifications().stream()
                    .map(q -> QualificationDTO.builder()
                            .degreeName(q.getQualification())
                            .yearOfPassing(q.getYear())
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
    public List<EmployeeResponseDto> findWithFilters(Department department,
                                                     Integer experience,
                                                     List<String> skills) {
       if(skills==null||skills.isEmpty()) {
           skills = null;
       }

        List<Employee> employees =
                employeeRepository.findWithFilters(department, experience, skills);

        return getEmployeeResponseDtos(employees);
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

//    @Override
//    public EmployeeResponseDto patchEmployee(Long id, EmployeePatchDto dto) {
//
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        // Optional: Check unique email
//        if (dto.getEmail() != null &&
//                !dto.getEmail().equals(employee.getEmail()) &&
//                employeeRepository.existsByEmail(dto.getEmail())) {
//
//            throw new RuntimeException("Email already exists");
//        }
//
//        employeeMapper.patchEmployee(dto, employee);
//
//        Employee saved = employeeRepository.save(employee);
//
//        return employeeMapper.toDto(saved);
//    }
}
