package com.imran.employeeapp.controllers;
import com.imran.employeeapp.dto.EmployeeDTO;
import com.imran.employeeapp.entity.Employee;
import com.imran.employeeapp.services.EmployeeService;
import com.imran.employeeapp.util.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/employees/bloodGroup/{bloodGroup}")
    public List<EmployeeDTO>getEmployeesByBloodGroup(@PathVariable String bloodGroup){
        return employeeService.getAllEmployeesByBloodGroup(bloodGroup);
    }
    @PostMapping("/employees")
    @Validated
    public ResponseMessage<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employee = employeeService.storeEmployee(employeeDTO);
        return new ResponseMessage<EmployeeDTO>("Employee stored successfully", employee);
    }

}
