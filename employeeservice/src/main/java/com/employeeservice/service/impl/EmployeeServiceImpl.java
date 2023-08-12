package com.employeeservice.service.impl;

import com.employeeservice.entity.Employee;
import com.employeeservice.mapper.dto.APIResponseDto;
import com.employeeservice.mapper.dto.DepartmentDto;
import com.employeeservice.mapper.dto.EmployeeDto;
import com.employeeservice.mapper.dto.EmployeeMapper;
import com.employeeservice.repository.EmployeeRepository;
import com.employeeservice.service.APIClient;
import com.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private APIClient apiClient;
    //private RestTemplate restTemplate;
    //private WebClient webClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveDEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveDEmployee);
        return savedEmployeeDto;
    }


     public APIResponseDto getEmployeeByID(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).get();

         /* ResponseEntity<DepartmentDto> responseEntity =  restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
          DepartmentDto departmentDto = responseEntity.getBody();*/

         /*DepartmentDto departmentDto = webClient.get()
                 .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                 .retrieve().bodyToMono(DepartmentDto.class).block();*/


        System.out.println("====================="+employee.getDepartmentCode());

         DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

         APIResponseDto apiResponseDto = new APIResponseDto();
         apiResponseDto.setEmployee(employeeDto);
         apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }


}
