package com.example.EmployeeManagementApp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T data;
}
