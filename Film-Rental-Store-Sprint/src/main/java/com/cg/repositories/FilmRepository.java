package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>{
	
	 List<Film> findByTitle(String title);
}
