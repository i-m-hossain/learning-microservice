package com.example.addressservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class AddressDto {
    @NotBlank(message="lane1 is required")
    private String lane1;
    private String lane2;
    private String state;
    @NotBlank(message = "zip code is required")
    private String zip;

    public String getLane1() {
        return lane1;
    }

    public void setLane1(String lane1) {
        this.lane1 = lane1;
    }

    public String getLane2() {
        return lane2;
    }

    public void setLane2(String lane2) {
        this.lane2 = lane2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
