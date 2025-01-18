package com.cg.dto;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
public class CustomerDTO {
	@NotNull(message = "Customer ID cannot be null")
    private Short customerId;
	
	 @NotNull(message = "First name cannot be null")
	 @Size(max = 45, message = "First name cannot exceed 45 characters")
     private String firstName;
	 
	 @NotNull(message = "Last name cannot be null")
	 @Size(max = 45, message = "Last name cannot exceed 45 characters")    
     private String lastName;
	 
	 @Email(message = "Email should be valid")
	 @Size(max = 50, message = "Email cannot exceed 50 characters")
     private String email;
	 
    public CustomerDTO() {
	}
 
	public CustomerDTO(Short customerId, String firstName, String lastName, String email) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
 
	}
 
	// Getters and Setters
    public Short getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
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
 
    
}
