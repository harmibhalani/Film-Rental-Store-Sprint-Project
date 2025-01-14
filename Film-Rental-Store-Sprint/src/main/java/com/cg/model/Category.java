package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "TINYINT UNSIGNED")
    private Short categoryId;

    @NotBlank(message = "Category name is required")
    @Size(max = 25, message = "Category name must not exceed 25 characters")
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Default constructor
    public Category() {
    }

    // All-args constructor
    public Category(Short categoryId, String name, LocalDateTime lastUpdate) {
        this.categoryId = categoryId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    // Getters
    public Short getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    // Setters
    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId) &&
               Objects.equals(name, category.name) &&
               Objects.equals(lastUpdate, category.lastUpdate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name, lastUpdate);
    }

    // toString method
    @Override
    public String toString() {
        return "Category{" +
               "categoryId=" + categoryId +
               ", name='" + name + '\'' +
               ", lastUpdate=" + lastUpdate +
               '}';
    }
}