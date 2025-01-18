package com.cg.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rental")
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer rentalId;
    
    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate;
    
    @Column(name = "inventory_id", nullable = false, columnDefinition = "MEDIUMINT UNSIGNED")
    private Integer inventoryId;
    
    @Column(name = "customer_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Short customerId;
    
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    
    @Column(name = "staff_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Short staffId;
    
    @Column(name = "last_update", nullable = false, updatable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", insertable = false, updatable = false)
    private Inventory inventory;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", insertable = false, updatable = false)
    private Staff staff;

    // Default Constructor
    public Rental() {
    }

    // Constructor with required fields
    public Rental(LocalDateTime rentalDate, Integer inventoryId, Short customerId, Short staffId) {
        this.rentalDate = rentalDate;
        this.inventoryId = inventoryId;
        this.customerId = customerId;
        this.staffId = staffId;
    }

    // Constructor with all fields
    public Rental(Integer rentalId, LocalDateTime rentalDate, Integer inventoryId, 
                 Short customerId, LocalDateTime returnDate, Short staffId, 
                 LocalDateTime lastUpdate) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventoryId = inventoryId;
        this.customerId = customerId;
        this.returnDate = returnDate;
        this.staffId = staffId;
        this.lastUpdate = lastUpdate;
    }

    // Getters
    public Integer getRentalId() {
        return rentalId;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public Short getCustomerId() {
        return customerId;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public Short getStaffId() {
        return staffId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Staff getStaff() {
        return staff;
    }

    // Setters
    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setCustomerId(Short short1) {
        this.customerId = short1;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setStaffId(Short short1) {
        this.staffId = short1;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @PrePersist
    protected void onCreate() {
        lastUpdate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(rentalId, rental.rentalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", rentalDate=" + rentalDate +
                ", inventoryId=" + inventoryId +
                ", customerId=" + customerId +
                ", returnDate=" + returnDate +
                ", staffId=" + staffId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}