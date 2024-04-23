package com.example.addressservice.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressDto {
    @NotBlank(message="lane1 is required")
    private String lane1;
    private String lane2;
    private String state;
    @NotBlank(message = "zip code is required")
    private String zip;
    private int employee_id;
}
