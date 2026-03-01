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

import static com.example.EmployeeManagementApp.util.StatusMessages.EMPLOYEE_NOT_FOUND;

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
        List<Employee> entities = chunk.stream().map(employeeHelperService::mapToEntity).toList();
        List<Employee> saved = employeeRepository.saveAll(entities);
        return saved.stream().map(employeeHelperService::mapToResponseDto).toList();
    }

    private void processIndividualErrors(List<EmployeeDTO> chunk,
                                         List<EmployeeResponseDto> successList,
                                         List<FailedRecordDetails> failList) {
        for (EmployeeDTO dto : chunk) {
            try {
                // Individual save to isolate the error
                Employee saved = employeeRepository.save(employeeHelperService.mapToEntity(dto));
                successList.add(employeeHelperService.mapToResponseDto(saved));
            } catch (Exception e) {
                failList.add(new FailedRecordDetails(dto, e.getMessage()));
            }
        }
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

    @Override
    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException(EMPLOYEE_NOT_FOUND + id);
        }

        employeeRepository.deleteById(id);
    }


    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeDTO dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(dto.getHireDate());
        employee.setExperience(dto.getExperience());
        employee.setSkills(dto.getSkills());


        employee.getQualifications().clear();

        if (dto.getQualifications() != null) {
            for (EmployeeQualificationDTO qdto : dto.getQualifications()) {

                EmployeeQualification qualification = new EmployeeQualification();
                qualification.setQualification(qdto.getQualification());
                qualification.setInstitution(qdto.getInstitution());
                qualification.setYear(qdto.getYear());
                qualification.setEmployee(employee);
                employee.getQualifications().add(qualification);
            }
        }

        return employeeHelperService.getResponseDtoFromEntity(employee);
    }

}
