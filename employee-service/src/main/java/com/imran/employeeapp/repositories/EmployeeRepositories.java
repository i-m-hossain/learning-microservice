package com.imran.employeeapp.repositories;
import com.imran.employeeapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepositories extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    List<Employee> findByEmail(String email);
    @Query("SELECT e FROM Employee e WHERE e.bloodGroup like %?1%")
    List<Employee> findByBloodGroup(String bloodGroup);
}
