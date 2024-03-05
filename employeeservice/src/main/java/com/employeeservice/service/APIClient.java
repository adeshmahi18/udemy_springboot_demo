package com.employeeservice.service;

import com.employeeservice.mapper.dto.DepartmentDto;
import com.employeeservice.service.impl.DepartmentFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE", url = "http://lb://DEPARTMENT:8080", fallback = DepartmentFallBack.class)
public interface APIClient {


    @GetMapping("/api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
