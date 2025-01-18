package com.cg.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Short addressId;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "address2", length = 50)
    private String address2;

    @Column(name = "district", nullable = false, length = 20)
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
    // Default constructor
    public Address() {
        this.lastUpdate = LocalDateTime.now();
    }
    public Address(String address3, City city2) {
		// TODO Auto-generated constructor stub
	}
 
	// Getters and Setters
    public Short getAddressId() {
        return addressId;
    }
    public void setAddressId(Short addressId) {
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
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
 
	public Address(Short addressId, String address, String address2, String district, City city, String postalCode,
			String phone, LocalDateTime lastUpdate) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.lastUpdate = lastUpdate;
	}

}