package com.cg.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "film_category")
public class Film_Category {
    
    @Id
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer filmId;
    
    @Id
    @Column(name = "category_id", columnDefinition = "TINYINT UNSIGNED")
    private Integer categoryId;
    
    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    private Film film;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;
    
    // Default constructor
    public Film_Category() {
    }
    
    // Constructor with required fields
    public Film_Category(Integer filmId, Integer categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }
    
    // Constructor with all fields
    public Film_Category(Integer filmId, Integer categoryId, LocalDateTime lastUpdate) {
        this.filmId = filmId;
        this.categoryId = categoryId;
        this.lastUpdate = lastUpdate;
    }
    
    // Getters
    public Integer getFilmId() {
        return filmId;
    }
    
    public Integer getCategoryId() {
        return categoryId;
    }
    
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    
    public Film getFilm() {
        return film;
    }
    
    public Category getCategory() {
        return category;
    }
    
    // Setters
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public void setFilm(Film film) {
        this.film = film;
    }
    
    public void setCategory(Category category) {
        this.category = category;
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
        Film_Category that = (Film_Category) o;
        return Objects.equals(filmId, that.filmId) && 
               Objects.equals(categoryId, that.categoryId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }
    
    @Override
    public String toString() {
        return "FilmCategory{" +
                "filmId=" + filmId +
                ", categoryId=" + categoryId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}