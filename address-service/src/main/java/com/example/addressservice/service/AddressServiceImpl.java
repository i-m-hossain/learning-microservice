package com.example.addressservice.service;

import com.example.addressservice.configuration.ModelConfig;
import com.example.addressservice.dto.AddressDto;
import com.example.addressservice.entity.Address;
import com.example.addressservice.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository repo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addressList =  repo.findAll();
        return addressList.stream()
                .map(address->modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddress(int employeeId) {
        Address address = repo.findById(employeeId).get();
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        return null;
    }
}
