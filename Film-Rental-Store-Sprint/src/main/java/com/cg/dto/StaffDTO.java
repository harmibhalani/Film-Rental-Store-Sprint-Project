package com.cg.dto;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
public class StaffDTO {
 
	private Short staffId;
	@NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;
 
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;
 
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
 
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
 
    @NotNull(message = "Active status is mandatory")
    private Boolean active;
 
    // Default constructor
    public StaffDTO() {}

    // Constructor
    public StaffDTO(Short staffId, String firstName, String lastName, String email, String username, Boolean active) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.active = active;
    }
 
    // Getters and Setters
    public Short getStaffId() {
        return staffId;
    }
 
    public void setStaffId(Short staffId) {
        this.staffId = staffId;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public Boolean getActive() {
        return active;
    }
 
    public void setActive(Boolean active) {
        this.active = active;
    }
}
