package com.imran.employeeapp.services;

import com.imran.employeeapp.dto.EmployeeDTO;
import com.imran.employeeapp.entity.Employee;
import com.imran.employeeapp.repositories.EmployeeRepositories;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepositories repo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = repo.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByBloodGroup(String bloodGroup) {
        List<Employee> employees = repo.findByBloodGroup(bloodGroup);
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO storeEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee = repo.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

}
