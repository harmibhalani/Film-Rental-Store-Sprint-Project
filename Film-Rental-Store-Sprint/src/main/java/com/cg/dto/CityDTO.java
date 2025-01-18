package com.cg.dto;
 
import java.time.LocalDateTime;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
 
public class CityDTO {
 
 
	@NotNull(message = "City ID cannot be null")
    private Integer cityId;
 
    @NotBlank(message = "City name cannot be blank")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private String city;
 
    @NotNull(message = "Country information is required")
    private CountryDTO country;
 
    @NotNull(message = "Last update cannot be null")
    @PastOrPresent(message = "Last update cannot be in the future")
    private LocalDateTime lastUpdate;

		public CityDTO(Integer cityId, String city, CountryDTO country, LocalDateTime lastUpdate) {
			super();
			this.cityId = cityId;
			this.city = city;
			this.country = country;
			this.lastUpdate = lastUpdate;
		}
		public Integer getCityId() {
			return cityId;
		}
		public void setCityId(Integer cityId) {
			this.cityId = cityId;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public CountryDTO getCountry() {
			return country;
		}
		public void setCountry(CountryDTO country) {
			this.country = country;
		}
		public LocalDateTime getLastUpdate() {
			return lastUpdate;
		}
		public void setLastUpdate(LocalDateTime lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

 
}
