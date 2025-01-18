package com.cg.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
public class CustomerCreateDTO {
	@NotNull(message = "Customer ID cannot be null")
    private Short customer_id;
	@NotNull(message = "Store ID cannot be null")
    private Short store_id;
	@NotBlank(message = "First name cannot be blank")
    @Size(max = 45, message = "First name cannot exceed 45 characters")
    private String first_name;
	@NotBlank(message = "Last name cannot be blank")
    @Size(max = 45, message = "Last name cannot exceed 45 characters")
    private String last_name;
	 @Email(message = "Email should be valid")
	 @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;
	 @NotNull(message = "Address ID cannot be null")
    private Short address_id;
    
 
    public CustomerCreateDTO() {
		
	}
 
	public CustomerCreateDTO(Short customer_id, Short store_id, String first_name, String last_name, String email,
			Short address_id) {
		super();
		this.customer_id = customer_id;
		this.store_id = store_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.address_id = address_id;
	}   
 
   
 
	public Short getCustomer_id() {
		return customer_id;
	}
 
	public void setCustomer_id(Short customer_id) {
		this.customer_id = customer_id;
	}
 
	public Short getStore_id() {
		return store_id;
	}
 
	public void setStore_id(Short store_id) {
		this.store_id = store_id;
	}
 
	public String getFirst_name() {
		return first_name;
	}
 
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
 
	public String getLast_name() {
		return last_name;
	}
 
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public Short getAddress_id() {
		return address_id;
	}
 
	public void setAddress_id(Short address_id) {
		this.address_id = address_id;
	}
 
	@Override
	public String toString() {
		return "CustomerCreateDTO [customer_id=" + customer_id + ", store_id=" + store_id + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", email=" + email + ", address_id=" + address_id + "]";
	}
 
 
 
	
}
