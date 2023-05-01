package com.nada.series.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nada.series.entities.Genre;
import com.nada.series.entities.Serie;

public interface SerieService {

	Serie saveSerie(Serie s);
	Serie updateSerie(Serie s);
	void deleteSerie(Serie s);
	void deleteSerieById(Long id);
	Serie getSerie(Long id);
	List<Serie> getAllSeries();
	
	 List<Genre> getAllGenres();
	 
	Page<Serie> getAllSeriesParPage(int page, int size);
	
	List<Serie> findByNomSerie(String nom);
	List<Serie> findByNomSerieContains(String nom);
	List<Serie> findByNomLangue (String nom, String langue );
	List<Serie> findByGenre (Genre genre);
	List<Serie> findByGenreIdGenre(Long id);
	List<Serie> findByOrderByNomSerieAsc();
	List<Serie> trierSeriesNomsLangue();

}
