package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Arrays;

@Entity
@Table(name = "staff", indexes = {
    @Index(name = "idx_fk_store_id", columnList = "store_id"),
    @Index(name = "idx_fk_address_id", columnList = "address_id")
})
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", columnDefinition = "TINYINT UNSIGNED")
    private Short staffId;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @NotNull(message = "Address is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", 
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_staff_address"))
    private Address address;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", columnDefinition = "tinyblob")
    private byte[] picture;

    @Email(message = "Please provide a valid email address")
    @Column(name = "email", length = 50)
    private String email;

    @NotNull(message = "Store is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", 
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_staff_store"))
    private Store store;

    @NotNull(message = "Active status is required")
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @NotBlank(message = "Username is required")
    @Size(max = 16, message = "Username must be no more than 16 characters")
    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", length = 40, 
            columnDefinition = "VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin")
    private String password;

    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Default constructor
    public Staff() {
    }

    // All-args constructor
    public Staff(Short staffId, String firstName, String lastName, Address address, 
                byte[] picture, String email, Store store, Boolean active, 
                String username, String password, LocalDateTime lastUpdate) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.picture = picture != null ? Arrays.copyOf(picture, picture.length) : null;
        this.email = email;
        this.store = store;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
    }

    // Getters
    public Short getStaffId() {
        return staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public byte[] getPicture() {
        return picture != null ? Arrays.copyOf(picture, picture.length) : null;
    }

    public String getEmail() {
        return email;
    }

    public Store getStore() {
        return store;
    }

    public Boolean getActive() {
        return active;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    // Setters
    public void setStaffId(Short staffId) {
        this.staffId = staffId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture != null ? Arrays.copyOf(picture, picture.length) : null;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(staffId, staff.staffId) &&
               Objects.equals(firstName, staff.firstName) &&
               Objects.equals(lastName, staff.lastName) &&
               Objects.equals(address, staff.address) &&
               Arrays.equals(picture, staff.picture) &&
               Objects.equals(email, staff.email) &&
               Objects.equals(store, staff.store) &&
               Objects.equals(active, staff.active) &&
               Objects.equals(username, staff.username) &&
               Objects.equals(password, staff.password) &&
               Objects.equals(lastUpdate, staff.lastUpdate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = Objects.hash(staffId, firstName, lastName, address, email,
                                store, active, username, password, lastUpdate);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    // toString method
    @Override
    public String toString() {
        return "Staff{" +
               "staffId=" + staffId +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", address=" + address +
               ", picture=" + (picture != null ? "[BLOB]" : "null") +
               ", email='" + email + '\'' +
               ", store=" + store +
               ", active=" + active +
               ", username='" + username + '\'' +
               ", password='[PROTECTED]'" +
               ", lastUpdate=" + lastUpdate +
               '}';
    }
}