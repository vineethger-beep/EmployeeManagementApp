package com.example.EmployeeManagementApp.repository;

import com.example.EmployeeManagementApp.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeQualificationRepository extends JpaRepository<Qualification,Long> {
}
