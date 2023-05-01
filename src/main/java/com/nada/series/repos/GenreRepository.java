package com.nada.series.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nada.series.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
