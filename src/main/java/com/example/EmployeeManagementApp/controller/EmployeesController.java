package com.example.EmployeeManagementApp.controller;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.response.ApiResponse;
import com.example.EmployeeManagementApp.dto.response.BatchProcessResponse;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                        .message("Employees saved successfully")
                        .data(batchProcessResponse)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> getFiltered(
            @RequestParam(required = false) Department department,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) List<String> skills) {

        List<EmployeeResponseDto> employees =
                employeeService.findWithFilters(department, experience, skills);

        ApiResponse<List<EmployeeResponseDto>> response =
                ApiResponse.<List<EmployeeResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Employees fetched successfully")
                        .data(employees)
                        .build();

        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Employee deleted successfully")
                        .data(null)
                        .build();

        return ResponseEntity.ok(response);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<ApiResponse<EmployeeResponseDto>> patchEmployee(
//            @PathVariable Long id,
//            @RequestBody EmployeePatchDto dto) {
//
//        EmployeeResponseDto updated =
//                employeeService.patchEmployee(id, dto);
//
//        ApiResponse<EmployeeResponseDto> response =
//                ApiResponse.<EmployeeResponseDto>builder()
//                        .statusCode(HttpStatus.OK.value())
//                        .message("Employee updated successfully")
//                        .data(updated)
//                        .build();
//
//        return ResponseEntity.ok(response);
//    }
}