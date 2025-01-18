package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "store", indexes = {
    @Index(name = "idx_fk_address_id", columnList = "address_id")
})
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", columnDefinition = "TINYINT UNSIGNED")
    private Short storeId;

    @NotNull(message = "Manager staff is required")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_staff_id", 
                nullable = false, 
               
                foreignKey = @ForeignKey(name = "fk_store_staff"))
    private Staff manager;

    @NotNull(message = "Address is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", 
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_store_address"))
    private Address address;

    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Bidirectional relationship with Staff
    @OneToMany(mappedBy = "store")
    private Set<Staff> staff = new HashSet<>();

    // Default constructor
    public Store() {
    }

    // All-args constructor
    public Store(Short storeId, Staff manager, Address address, LocalDateTime lastUpdate, Set<Staff> staff) {
        this.storeId = storeId;
        this.manager = manager;
        this.address = address;
        this.lastUpdate = lastUpdate;
        this.staff = staff != null ? new HashSet<>(staff) : new HashSet<>();
    }

    // Getters
    public Short getStoreId() {
        return storeId;
    }

    public Staff getManager() {
        return manager;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public Set<Staff> getStaff() {
        return new HashSet<>(staff);  // Return a copy to prevent external modification
    }

    // Setters
    public void setStoreId(Short storeId) {
        this.storeId = storeId;
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff != null ? new HashSet<>(staff) : new HashSet<>();
    }

    // Utility methods for managing the staff collection
    public void addStaff(Staff staffMember) {
        staff.add(staffMember);
        staffMember.setStore(this);
    }

    public void removeStaff(Staff staffMember) {
        staff.remove(staffMember);
        staffMember.setStore(null);
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(storeId, store.storeId) &&
               Objects.equals(manager, store.manager) &&
               Objects.equals(address, store.address) &&
               Objects.equals(lastUpdate, store.lastUpdate);
        // Note: staff set is intentionally excluded to prevent circular references
    }

    // hashCode method
    @Override
    public int hashCode() {
        // Note: staff set is intentionally excluded to prevent circular references
        return Objects.hash(storeId, manager, address, lastUpdate);
    }

    // toString method
    @Override
    public String toString() {
        return "Store{" +
               "storeId=" + storeId +
               ", manager=" + manager +
               ", address=" + address +
               ", lastUpdate=" + lastUpdate +
               ", staffCount=" + staff.size() +  // Only show count to prevent circular reference
               '}';
    }
}