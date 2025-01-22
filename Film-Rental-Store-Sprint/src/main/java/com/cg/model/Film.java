package com.cg.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Long filmId;
    
    @NotBlank(message = "Title is required and cannot be blank")
    @Size(max = 128, message = "Title cannot exceed 128 characters")
    @Column(name = "title", length = 128, nullable = false)
    private String title;
    
    @Size(max = 65535, message = "Description cannot exceed 65535 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Min(value = 1901, message = "Release year must be after 1900")
    @Max(value = 2155, message = "Release year cannot exceed 2155")
    @Column(name = "release_year", columnDefinition = "YEAR")
    private Integer releaseYear;

    @Column(name = "original_language_id", columnDefinition = "TINYINT UNSIGNED")
    private Short originalLanguageId;
    
    @NotNull(message = "Rental duration is required")
    @Min(value = 1, message = "Rental duration must be at least 1")
    @Max(value = 255, message = "Rental duration cannot exceed 255")
    @Column(name = "rental_duration", columnDefinition = "TINYINT UNSIGNED")
    private Short rentalDuration = 3;
    
    @NotNull(message = "Rental rate is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Rental rate must be greater than 0.00")
    @Digits(integer = 2, fraction = 2, message = "Rental rate must be a valid decimal with 2 digits before and after the decimal point")
    @Column(name = "rental_rate", columnDefinition = "DECIMAL(4,2)")
    private BigDecimal rentalRate = new BigDecimal("4.99");

    @Column(name = "length", columnDefinition = "SMALLINT UNSIGNED")
    private Integer length;
    
    @Column(name = "replacement_cost", columnDefinition = "DECIMAL(5,2)")
    private Double replacementCost = 19.99;
    
    @Pattern(regexp = "G|PG|PG-13|R|NC-17", message = "Rating must be one of G, PG, PG-13, R, or NC-17")
    @Column(name = "rating", columnDefinition = "ENUM('G','PG','PG-13','R','NC-17') DEFAULT 'G'")
    private String rating = "G";

    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;
    
    @NotNull(message = "Last update is required")
    @Column(name = "last_update", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp lastUpdate;
    
    //Relationships
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id") 
    private Language language; 
    
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Film_Actor> filmActors = new HashSet<>();
    
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Film_Category> filmCategories;
    
//    @ManyToMany
//    @JoinTable(name = "Film_Category", 
//               joinColumns = @JoinColumn(name = "filmId"), 
//               inverseJoinColumns = @JoinColumn(name = "categoryId"))
//    private Set<Category> categories;

    // Default constructor
    public Film() {
        super();
        this.lastUpdate = new Timestamp(System.currentTimeMillis()); 
    }

   // Constructor with all fields
   public Film(String title, String description, Integer releaseYear, Language language,
               Short originalLanguageId, Short rentalDuration, BigDecimal rentalRate,
               Integer length, Double replacementCost, String rating,
               String specialFeatures, Timestamp lastUpdate) {
       this.title = title;
       this.description = description;
       this.releaseYear = releaseYear;
       this.language = language; // Set Language object
       this.originalLanguageId = originalLanguageId;
       this.rentalDuration = rentalDuration;
       this.rentalRate = rentalRate;
       this.length = length;
       this.replacementCost = replacementCost;
       this.rating = rating;
       this.specialFeatures = specialFeatures;
       this.lastUpdate = lastUpdate;
   }

   // Getters and Setters
   public Long getFilmId() {
       return filmId;
   }

   public void setFilmId(Long filmId) {
       this.filmId = filmId;
   }

   public String getTitle() {
       return title;
   }

   public void setTitle(String title) {
       this.title = title;
   }

   public String getDescription() {
       return description;
   }

   public void setDescription(String description) {
       this.description = description;
   }

   public Integer getReleaseYear() {
       return releaseYear;
   }

   public void setReleaseYear(Integer releaseYear) {
       this.releaseYear = releaseYear;
   }

   public Language getLanguage() { // Updated getter
       return language; // Return the actual Language object
   }

   public void setLanguage(Language language) { // Updated setter
       this.language = language; // Set the Language object
   }
   
//   public List<Film_Category> getFilmCategories() {
//       return filmCategories; // Return list of Film_Category
//   }
//
//   public void setFilmCategories(List<Film_Category> filmCategories) {
//       this.filmCategories = filmCategories; // Set list of Film_Category
//   }

   public Short getOriginalLanguageId() {
       return originalLanguageId;
   }

   public void setOriginalLanguageId(Short originalLanguageId) {
       this.originalLanguageId = originalLanguageId;
   }

   public Short getRentalDuration() {
       return rentalDuration;
   }

   public void setRentalDuration(Short rentalDuration) {
       this.rentalDuration = rentalDuration;
   }

   public @NotNull(message = "Rental rate is required") @DecimalMin(value = "0.00", inclusive = false, message = "Rental rate must be greater than 0.00") @Digits(integer = 2, fraction = 2, message = "Rental rate must be a valid decimal with 2 digits before and after the decimal point") BigDecimal getRentalRate() {
       return rentalRate;
   }

   public void setRentalRate(BigDecimal rentalRate) {
       this.rentalRate = rentalRate;
   }

   public Integer getLength() {
       return length;
   }

   public void setLength(Integer length) {
       this.length = length;
   }

   public Double getReplacementCost() {
       return replacementCost;
   }

   public void setReplacementCost(Double replacementCost) {
       this.replacementCost = replacementCost;
   }

   public String getRating() {
       return rating;
   }

   public void setRating(String rating) {
       this.rating = rating;
   }

   public String getSpecialFeatures() {
       return specialFeatures;
   }

   public void setSpecialFeatures(String specialFeatures) {
       this.specialFeatures = specialFeatures;
   }

   public Timestamp getLastUpdate() {
       return lastUpdate;
   }

   public void setLastUpdate(Timestamp lastUpdate) {
       this.lastUpdate = lastUpdate;
   }
   
   public Set<Film_Actor> getFilmActors() {
       return filmActors;
   }

   public void setFilmActors(Set<Film_Actor> filmActors) {
       this.filmActors = filmActors;
   }
   
   @PrePersist
   protected void onCreate() {
       lastUpdate = new Timestamp(System.currentTimeMillis());
   }

   @PreUpdate
   protected void onUpdate() {
       lastUpdate = new Timestamp(System.currentTimeMillis());
   }

  @Override
  public int hashCode() {
      return Objects.hash(filmId);
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj)
          return true;
      if (obj == null)
          return false;
      if (getClass() != obj.getClass())
          return false;
      Film other = (Film) obj;
      return Objects.equals(filmId, other.filmId);
  }

public List<Film_Category> getFilmCategories() {
	return filmCategories;
}

public void setFilmCategories(List<Film_Category> filmCategories) {
	this.filmCategories = filmCategories;
}
}
