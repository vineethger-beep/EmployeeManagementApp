package com.example.EmployeeManagementApp.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchProcessResponse {
    private int totalRecords;
    private int successCount;
    private int failureCount;

    // Uses Response DTO to include the newly generated PostgreSQL IDs
    private List<EmployeeResponseDto> successfulRecords;

    // Uses the original DTOs so the user can fix and re-submit
    private List<FailedRecordDetails> failedRecords;
}
