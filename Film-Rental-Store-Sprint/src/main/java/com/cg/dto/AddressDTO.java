package com.cg.dto;
 
import com.cg.model.City;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
 
public class AddressDTO {
 
	@NotNull(message = "Address ID cannot be null")
    private Long addressId; 
 
    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;
 
    @Size(max = 255, message = "Address line 2 must not exceed 255 characters")
    private String address2; 
 
    @NotBlank(message = "District cannot be blank")
    @Size(max = 100, message = "District must not exceed 100 characters")
    private String district;
 
    @NotNull(message = "City cannot be null")
    private City city;
 
    @NotBlank(message = "Postal code cannot be blank")
    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    private String postalCode;
 
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\+?[0-9\\-]+", message = "Phone number must be valid")
    private String phone;

		public Long getAddressId() {
			return addressId;
		}
		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getAddress2() {
			return address2;
		}
		public void setAddress2(String address2) {
			this.address2 = address2;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public City getCity() {
			return city;
		}
		public void setCity(City city) {
			this.city = city;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public AddressDTO(Long addressId, String address, String address2, String district, City city,
				String postalCode, String phone) {
			super();
			this.addressId = addressId;
			this.address = address;
			this.address2 = address2;
			this.district = district;
			this.city = city;
			this.postalCode = postalCode;
			this.phone = phone;
		}
 
    
}