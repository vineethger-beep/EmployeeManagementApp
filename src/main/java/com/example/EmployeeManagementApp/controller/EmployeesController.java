package com.example.EmployeeManagementApp.controller;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.response.ApiResponse;
import com.example.EmployeeManagementApp.dto.response.BatchProcessResponse;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.EmployeeManagementApp.util.StatusMessages.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeeService employeeService;

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse<BatchProcessResponse>> batchInsert(
            @RequestBody List<EmployeeDTO> dtos) {

        BatchProcessResponse batchProcessResponse =
                employeeService.saveEmployees(dtos);

        ApiResponse<BatchProcessResponse> response =
                ApiResponse.<BatchProcessResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(EMPLOYEES_SAVED)
                        .data(batchProcessResponse)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeResponseDto>>> getFiltered(
            @RequestParam(required = false) Department department,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) List<String> skills,
            Pageable pageable) {

        Page<EmployeeResponseDto> employees =
                employeeService.findWithFilters(department, experience, skills, pageable);

        ApiResponse<Page<EmployeeResponseDto>> response =
                ApiResponse.<Page<EmployeeResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(EMPLOYEES_FETCHED_SUCCESSFULLY)
                        .data(employees)
                        .build();

        return ResponseEntity
                .ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(EMPLOYEE_DELETED)
                        .data(null)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> updateEmployee(@PathVariable Long id,
                                                                           @RequestBody EmployeeDTO dto) {
        EmployeeResponseDto employeeResponseDto = employeeService.updateEmployee(id, dto);
        ApiResponse<EmployeeResponseDto> response = ApiResponse.<EmployeeResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message(EMPLOYEE_UPDATED)
                .data(null)
                .build();
        return ResponseEntity.ok().body(response);
    }
}