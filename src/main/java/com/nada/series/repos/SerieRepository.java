package com.nada.series.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nada.series.entities.Genre;
import com.nada.series.entities.Serie;

@RepositoryRestResource(path = "rest")
public interface SerieRepository extends JpaRepository<Serie,Long> {

	List<Serie> findByNomSerie(String nom);
	List <Serie> findByNomSerieContains(String nom);
	
//	@Query("select s from Serie s where s.nomSerie like %?1 and s.langue > ?2")
//	List<Serie> findByNomLangue (String nom, String langue);
	
	@Query("select s from Serie s where s.nomSerie like %:nom and s.langue > :langue")
	List<Serie> findByNomLangue (@Param("nom") String nom,@Param("langue") String langue);

	@Query("select s from Serie s where s.genre = ?1")
	List<Serie> findByGenre (Genre genre);
	
	List<Serie> findByGenreIdGenre(Long id);
	
	List<Serie> findByOrderByNomSerieAsc();
	
	@Query("select s from Serie s order by s.nomSerie ASC, s.langue DESC")
	List<Serie> trierSeriesNomsLangue ();

}
