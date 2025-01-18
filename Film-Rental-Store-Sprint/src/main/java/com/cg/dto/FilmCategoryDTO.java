package com.cg.dto;

import java.time.LocalDateTime;

public class FilmCategoryDTO {
    private Integer filmId;
    private Integer categoryId;
    private String categoryName;
    private LocalDateTime lastUpdate;

    public FilmCategoryDTO(Integer filmId, Integer categoryId, String categoryName) {
        this.filmId = filmId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.lastUpdate = LocalDateTime.now();
    }

    // Getters and setters
    public Integer getFilmId() { return filmId; }
    public void setFilmId(Integer filmId) { this.filmId = filmId; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
}