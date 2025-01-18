package com.cg.dto;
 
import java.time.LocalDateTime;
import java.util.List;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
 
public class CountryDTO {
 
 
	
	@NotNull(message = "Country ID cannot be null")
    private Long countryId;
 
    @NotBlank(message = "Country name cannot be blank")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private String country;
 
    @NotNull(message = "Last update cannot be null")
    @PastOrPresent(message = "Last update cannot be in the future")
    private LocalDateTime lastUpdate;
 
    @NotEmpty(message = "City IDs list cannot be empty")
    private List<@NotNull(message = "City ID cannot be null") Long> cityIds;
 
    

	public CountryDTO(Long countryId, String country, LocalDateTime lastUpdate, List<Long> cityIds) {
		super();
		this.countryId = countryId;
		this.country = country;
		this.lastUpdate = lastUpdate;
		this.cityIds = cityIds;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public List<Long> getCityIds() {
		return cityIds;
	}
	public void setCityIds(List<Long> cityIds) {
		this.cityIds = cityIds;
	}

}
