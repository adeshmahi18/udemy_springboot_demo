package com.employeeservice.controller;

import com.employeeservice.mapper.dto.APIResponseDto;
import com.employeeservice.mapper.dto.EmployeeDto;
import com.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeByID(@PathVariable("id") Long employeeId){

        System.out.print(employeeId);
        APIResponseDto apiResponseDto = employeeService.getEmployeeByID(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
