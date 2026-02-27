package com.example.EmployeeManagementApp.dto.response;

import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailedRecordDetails {
    private EmployeeDTO record; // The original input DTO
    private String errorMessage;
}
