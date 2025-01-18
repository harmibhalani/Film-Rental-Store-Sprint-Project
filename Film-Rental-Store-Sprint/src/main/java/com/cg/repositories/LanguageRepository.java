package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.model.Film;
import com.cg.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Short>{
	
	Language findByName(String name);
//	List<Film> findByLanguageName(Language language);
}
