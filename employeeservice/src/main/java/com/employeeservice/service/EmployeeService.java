package com.employeeservice.service;

import com.employeeservice.mapper.dto.APIResponseDto;
import com.employeeservice.mapper.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeByID(Long employeeId);
}
