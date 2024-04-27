package com.example.addressservice.controller;

import com.example.addressservice.dto.AddressDto;
import com.example.addressservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> getAllAddress(){
        List<AddressDto> result = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<AddressDto> getEmployeeAddress(@PathVariable  int employeeId)
    {
        AddressDto result = addressService.getAddressByEmployeeId(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
