package com.example.addressservice.repositories;

import com.example.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(nativeQuery = true,value = "SELECT ea.id, ea.lane1, ea.lane2, ea.state,  ea.zip, ea.employee_id FROM `employee-service`.addresses as ea join `employee-service`.employees as e on e.id=ea.employee_id where ea.employee_id =:employeeId ")
    Address findAddressesByEmployeeId(@Param("employeeId") int employeeId);
}
