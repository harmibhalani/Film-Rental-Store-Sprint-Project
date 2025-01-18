package com.cg.model;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
 
@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Short customerId;
 
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
 
    @NotNull
    @Size(max = 45)
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
 
    @NotNull
    @Size(max = 45)
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
 
    @Email
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;
 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
 
    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = true;
 
    @NotNull
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    
    @NotNull
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
 
   
    public Customer() {
        this.createDate = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
        this.active = true;
    }
 
    
    public Short getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }
 
    public Store getStore() {
        return store;
    }
 
    public void setStore(Store store) {
        this.store = store;
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
 
    public Address getAddress() {
        return address;
    }
 
    public void setAddress(Address address) {
        this.address = address;
    }
 
    public Boolean getActive() {
        return active;
    }
 
    public void setActive(Boolean active) {
        this.active = active;
    }
 
    public LocalDateTime getCreateDate() {
        return createDate;
    }
 
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
 
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
 
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
 