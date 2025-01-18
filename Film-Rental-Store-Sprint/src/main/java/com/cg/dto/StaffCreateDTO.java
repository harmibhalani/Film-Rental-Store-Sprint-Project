package com.cg.dto;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
 
public class StaffCreateDTO {
    @NotBlank(message = "First name is required")
    private String firstName;
 
    @NotBlank(message = "Last name is required")
    private String lastName;
 
    @NotNull(message = "Address is required")
    private Long addressId; // Assuming you have an Address ID to link
 
    @Email(message = "Please provide a valid email address")
    private String email;
 
    @NotNull(message = "Store is required")
    private Short storeId; // Assuming you have a Store ID to link
 
    @NotBlank(message = "Username is required")
    @Size(max = 16, message = "Username must be no more than 16 characters")
    private String username;
 
    @NotBlank(message = "Password is required")
    private String password;
 
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
 
	public Long getAddressId() {
		return addressId;
	}
 
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public Short getStoreId() {
		return storeId;
	}
 
	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}
 
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
    
}