package com.employeeservice.controller;

import com.employeeservice.mapper.dto.APIResponseDto;
import com.employeeservice.mapper.dto.EmployeeDto;
import com.employeeservice.service.EmployeeService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
  public class EmployeeController {
    ////-Dspring.profiles.active=test
    @Autowired
    private EmployeeService employeeService;


    @Value("${welcome.message}")
    private String appName;

    // Build Save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeByID(@PathVariable("id") Long employeeId){

        System.out.print("appName==="+appName);
        APIResponseDto apiResponseDto = employeeService.getEmployeeByID(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
