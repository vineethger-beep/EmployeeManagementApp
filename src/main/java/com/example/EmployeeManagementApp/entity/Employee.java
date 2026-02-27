package com.example.EmployeeManagementApp.entity;

import com.example.EmployeeManagementApp.constants.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Department department;
    private Double salary;
    private LocalDate hireDate;
    private Integer experience;
    @ElementCollection
    @CollectionTable(
            name = "employee_skills",
            joinColumns = @JoinColumn(name = "employee_id")
    )
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Qualification> qualifications = new ArrayList<>();

}
