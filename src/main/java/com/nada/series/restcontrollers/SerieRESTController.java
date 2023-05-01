package com.nada.series.restcontrollers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nada.series.entities.Serie;
import com.nada.series.service.SerieService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SerieRESTController {

	@Autowired
	SerieService serieService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Serie> getAllSerie(){
	    try {
	        List<Serie> series = serieService.getAllSeries();
	        if(series.isEmpty()) {
	            throw new Exception("Aucune série n'existe dans la base de données");
	        }
	        return series;
	    } catch (Exception e) {
	        // Générer un message d'erreur ou une réponse d'erreur personnalisée
	        System.err.println("Erreur : " + e.getMessage());
	        return null;
	    }
	}

	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Serie getSerieById(@PathVariable("id")Long id) {
	    try {
	        return serieService.getSerie(id);
	    } catch (NoSuchElementException ex) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La série avec l'id " + id + " n'existe pas", ex);
	    }
	}

	@RequestMapping(method = RequestMethod.POST)
	public Serie createSerie(@RequestBody Serie serie) {
		return serieService.saveSerie(serie);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Serie updateSerie(@RequestBody Serie serie) {
		return serieService.updateSerie(serie);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteSerie(@PathVariable("id") Long id) {
		serieService.deleteSerieById(id);
	}
	
	@RequestMapping(value="/seriesgenre/{idGenre}",method = RequestMethod.GET)
	public List<Serie> getSeriesByGenreId(@PathVariable("idGenre") Long idGenre) {
	return serieService.findByGenreIdGenre(idGenre);
	}

}
