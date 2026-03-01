package com.example.EmployeeManagementApp.entity;


import com.example.EmployeeManagementApp.constants.DegreeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_qualifications")
@Builder
public class EmployeeQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DegreeType qualification; // The Enum part

    private String institution;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
