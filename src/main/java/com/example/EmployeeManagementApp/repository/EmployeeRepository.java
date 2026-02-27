package com.example.EmployeeManagementApp.repository;

import com.example.EmployeeManagementApp.constants.Department;
import com.example.EmployeeManagementApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("""
    SELECT e
    FROM Employee e
    WHERE (:department IS NULL OR e.department = :department)
      AND (:experience IS NULL OR e.experience >= :experience)
                  AND (
                        :skills IS NULL OR
                        EXISTS (
                            SELECT s
                            FROM e.skills s
                            WHERE s IN :skills
                        )
                      )
""")
    List<Employee> findWithFilters(@Param("department") Department department,
                                   @Param("experience") Integer experience,
                                   @Param("skills") List<String> skills);

    boolean existsByEmail(String email);
}


