package com.example.addressservice.service;

import com.example.addressservice.dto.AddressDto;
import com.example.addressservice.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AddressService {
    public List<AddressDto> getAllAddresses();
    public AddressDto getAddress(int employeeId);
    public AddressDto saveAddress(AddressDto addressDto);
}
