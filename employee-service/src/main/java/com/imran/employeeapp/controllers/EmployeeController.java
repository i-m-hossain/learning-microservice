package com.imran.employeeapp.controllers;
import com.imran.employeeapp.dto.EmployeeDTO;
import com.imran.employeeapp.entity.Employee;
import com.imran.employeeapp.services.EmployeeService;
import com.imran.employeeapp.util.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employees=  employeeService.getAllEmployees();
        return ResponseMessage.createResponse(HttpStatus.OK, employees);
    }
    @GetMapping("/employees/bloodGroup/{bloodGroup}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByBloodGroup(@PathVariable String bloodGroup){
        List<EmployeeDTO> employees =  employeeService.getAllEmployeesByBloodGroup(bloodGroup);
        return ResponseMessage.createResponse(HttpStatus.OK, employees);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id)
    {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseMessage.createResponse(HttpStatus.OK, employee);
    }
    @PostMapping("/employees")
    @Validated
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO)
    {
            try {
                EmployeeDTO employee = employeeService.storeEmployee(employeeDTO);
                return ResponseMessage.createResponse(HttpStatus.CREATED, employee, "Employee created successfully");
            }catch (IllegalArgumentException ex) {
                return ResponseMessage.createResponse(HttpStatus.BAD_REQUEST, "Invalid argument provided");
            }catch(DataIntegrityViolationException ex){
                return ResponseMessage.createResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            }catch (Exception ex) {
                return ResponseMessage.createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
            }
    }

}
