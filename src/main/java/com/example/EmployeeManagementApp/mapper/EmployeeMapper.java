package com.example.EmployeeManagementApp.mapper;

import com.example.EmployeeManagementApp.dto.EmployeePatchDto;
import com.example.EmployeeManagementApp.dto.request.EmployeeDTO;
import com.example.EmployeeManagementApp.dto.response.EmployeeResponseDto;
import com.example.EmployeeManagementApp.entity.Employee;

import java.util.List;

//@Mapper(
//        componentModel = "spring",
//        uses = {QualificationMapper.class}
//)
//public interface EmployeeMapper {
//
//    Employee toEntity(EmployeeDTO dto);
//
//    EmployeeResponseDto toDto(Employee employee);
//
//    List<EmployeeResponseDto> toDtoList(List<Employee> employees);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateEmployeeFromDto(EmployeeDTO dto,
//                               @MappingTarget Employee employee);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void patchEmployee(EmployeePatchDto dto,
//                       @MappingTarget Employee employee);
//}