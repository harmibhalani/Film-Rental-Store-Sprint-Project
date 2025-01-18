package com.cg.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FilmCategoryDTO {
    private Long filmId;
    private Long categoryId;
    private String categoryName;
    private Timestamp lastUpdate;
    
	public Long getFilmId() {
		return filmId;
	}
	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long integer) {
		this.categoryId = integer;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp timestamp) {
		this.lastUpdate = timestamp;
	}
	public FilmCategoryDTO(Long filmId, Long categoryId, String categoryName, Timestamp lastUpdate) {
		super();
		this.filmId = filmId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.lastUpdate = lastUpdate;
	}

	public FilmCategoryDTO() {
		// TODO Auto-generated constructor stub
	}
	public FilmCategoryDTO(Long filmId2, Long categoryId2) {
		// TODO Auto-generated constructor stub
	}




}