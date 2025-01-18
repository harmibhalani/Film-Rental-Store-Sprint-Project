package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "language")
public class Language {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", columnDefinition = "TINYINT UNSIGNED")
    private Short languageId;

    @NotNull(message = "Name is required")
    @Size(max = 20, message = "Name can be at most 20 characters long")
    @Column(name = "name", nullable = false, columnDefinition = "CHAR(20)")
    private String name;

    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Default constructor
    public Language() {
        this.lastUpdate = LocalDateTime.now();
    }

    // All-args constructor
    public Language(Short languageId, String name, LocalDateTime lastUpdate) {
        this.languageId = languageId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    // Getters
    public Short getLanguageId() {
        return languageId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    // Setters
    public void setLanguageId(Short languageId) {
        this.languageId = languageId;
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
        Language language = (Language) o;
        return Objects.equals(languageId, language.languageId) &&
               Objects.equals(name, language.name) &&
               Objects.equals(lastUpdate, language.lastUpdate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(languageId, name, lastUpdate);
    }

    // toString method
    @Override
    public String toString() {
        return "Language{" +
               "languageId=" + languageId +
               ", name='" + name + '\'' +
               ", lastUpdate=" + lastUpdate +
               '}';
    }

	public Language orElseThrow() {
		// TODO Auto-generated method stub
		return null;
	}
}