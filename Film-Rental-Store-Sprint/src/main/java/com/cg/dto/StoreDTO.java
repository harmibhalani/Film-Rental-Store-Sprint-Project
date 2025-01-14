package com.cg.dto;

public class StoreDTO {
    private Short storeId;
    private String managerName;
    private String address;
    private String city;
    private String country;
    private String phone;

    // Constructor
    public StoreDTO(Short storeId, String managerName, String address, 
                    String city, String country, String phone) {
        this.storeId = storeId;
        this.managerName = managerName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    // Getters and Setters
    public Short getStoreId() {
        return storeId;
    }

    public void setStoreId(Short storeId) {
        this.storeId = storeId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}