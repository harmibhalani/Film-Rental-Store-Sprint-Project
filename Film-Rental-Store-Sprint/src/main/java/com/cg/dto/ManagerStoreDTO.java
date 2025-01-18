package com.cg.dto;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
 
public class ManagerStoreDTO {
	@NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;
 
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;
 
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;
 
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\+?[0-9\\-]+", message = "Phone number must be valid")
    private String phone;
 
    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;
 
    @NotBlank(message = "City cannot be blank")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private String city;
	public ManagerStoreDTO(String firstName, String lastName, String email, String phone, String address, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
	}

	public ManagerStoreDTO() {
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}