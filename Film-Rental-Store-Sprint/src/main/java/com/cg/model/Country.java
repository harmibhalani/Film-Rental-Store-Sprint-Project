package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer countryId;

    @NotBlank(message = "Country name is required")
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Default constructor
    public Country() {
    }

    // All-args constructor
    public Country(Integer countryId, String country, LocalDateTime lastUpdate) {
        this.countryId = countryId;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    // Getters
    public Integer getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    // Setters
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryId, country.countryId) &&
               Objects.equals(this.country, country.country) &&
               Objects.equals(lastUpdate, country.lastUpdate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(countryId, country, lastUpdate);
    }

    // toString method
    @Override
    public String toString() {
        return "Country{" +
               "countryId=" + countryId +
               ", country='" + country + '\'' +
               ", lastUpdate=" + lastUpdate +
               '}';
    }
}