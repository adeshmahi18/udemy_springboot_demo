package com.employeeservice.service.impl;

import com.employeeservice.mapper.dto.DepartmentDto;
import com.employeeservice.service.APIClient;
import org.springframework.stereotype.Component;

@Component
public class DepartmentFallBack implements APIClient {
    @Override
    public DepartmentDto getDepartment(String departmentCode) {
        return null;
    }
}
