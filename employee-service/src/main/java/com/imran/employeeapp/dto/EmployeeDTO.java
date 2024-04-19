package com.imran.employeeapp.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class EmployeeDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not valid")
    private String email;
    @NotBlank(message = "Blood Group is required")
    private String bloodGroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}