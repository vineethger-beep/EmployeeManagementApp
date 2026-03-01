package com.example.EmployeeManagementApp.dto.request;

import com.example.EmployeeManagementApp.constants.DegreeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeQualificationDTO {

    private Long id;
    private DegreeType qualification;
    private String institution;
    private Integer year;
}
