package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "city", indexes = { @Index(name = "idx_fk_country_id", columnList = "country_id") })
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer cityId;

    @NotBlank(message = "City name is required")
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotNull(message = "Country is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "fk_city_country"))
    private Country country;

    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Default constructor
    public City() {
    }

    // All-args constructor
    public City(Integer cityId, String city, Country country, LocalDateTime lastUpdate) {
        this.cityId = cityId;
        this.city = city;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    // Getters
    public Integer getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    // Setters
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(Country country) {
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
        City city = (City) o;
        return Objects.equals(cityId, city.cityId) &&
               Objects.equals(this.city, city.city) &&
               Objects.equals(country, city.country) &&
               Objects.equals(lastUpdate, city.lastUpdate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(cityId, city, country, lastUpdate);
    }

    // toString method
    @Override
    public String toString() {
        return "City{" +
               "cityId=" + cityId +
               ", city='" + city + '\'' +
               ", country=" + country +
               ", lastUpdate=" + lastUpdate +
               '}';
    }
}