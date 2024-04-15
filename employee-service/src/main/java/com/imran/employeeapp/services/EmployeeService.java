package com.imran.employeeapp.services;

import com.imran.employeeapp.dto.EmployeeDTO;
import com.imran.employeeapp.entity.Employee;


import java.util.List;

public interface EmployeeService {
    public List<EmployeeDTO> getAllEmployees();
    public List<EmployeeDTO> getAllEmployeesByBloodGroup(String bloodGroup);
    public EmployeeDTO storeEmployee(EmployeeDTO employeeDTO);
}
