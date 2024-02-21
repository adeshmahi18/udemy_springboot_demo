package com.employeeservice.controller;

import com.employeeservice.mapper.dto.APIResponseDto;
import com.employeeservice.mapper.dto.EmployeeDto;
import com.employeeservice.service.EmployeeService;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
 public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //@Value("${app.name}")
    //private String appName;

    // Build Save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeByID(@PathVariable("id") Long employeeId){

        //System.out.print("appName==="+appName);
        APIResponseDto apiResponseDto = employeeService.getEmployeeByID(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
