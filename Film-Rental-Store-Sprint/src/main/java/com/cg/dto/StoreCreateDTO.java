package com.cg.dto;
 
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
 
public class StoreCreateDTO {

//	 @NotNull(message = "Manager ID cannot be null")
	    @Positive(message = "Manager ID must be a positive number")
	    private Short managerId;
 
	    @NotNull(message = "Address ID cannot be null")
	    @Positive(message = "Address ID must be a positive number")
	    private Long addressId; 
 
		public Short getManagerId() {
			return managerId;
		}
 
		public void setManagerId(Short managerId) {
			this.managerId = managerId;
		}
 
		public Long getAddressId() {
			return addressId;
		}
 
		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}
  
}
 