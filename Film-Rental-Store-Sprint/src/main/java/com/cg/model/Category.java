package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "TINYINT UNSIGNED")
    private Long categoryId;

    @NotBlank(message = "Category name is required")
    @Size(max = 25, message = "Category name must not exceed 25 characters")
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Film_Category> filmCategories; 

    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Default constructor
    public Category() {
    }

    // All-args constructor
    public Category(Long categoryId, String name, LocalDateTime lastUpdate) {
        this.categoryId = categoryId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    // Getters
    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    // Setters
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
//    public List<Film_Category> getFilmCategories() {
//        return filmCategories; // Return list of Film_Category
//    }
//
//    public void setFilmCategories(List<Film_Category> filmCategories) {
//        this.filmCategories = filmCategories; // Set list of Film_Category
//    }

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

	public List<Film_Category> getFilmCategories() {
		return filmCategories;
	}

	public void setFilmCategories(List<Film_Category> filmCategories) {
		this.filmCategories = filmCategories;
	}

	public Category(Long categoryId,
			@NotBlank(message = "Category name is required") @Size(max = 25, message = "Category name must not exceed 25 characters") String name,
			List<Film_Category> filmCategories, LocalDateTime lastUpdate) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.filmCategories = filmCategories;
		this.lastUpdate = lastUpdate;
	}
    
}