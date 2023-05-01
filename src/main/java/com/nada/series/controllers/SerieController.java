package com.nada.series.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nada.series.entities.Genre;
import com.nada.series.entities.Serie;
import com.nada.series.service.SerieService;

import jakarta.validation.Valid;

@Controller
public class SerieController {

	@Autowired
	SerieService serieService;

//	@RequestMapping("/showCreate")
//	public String showCreate(ModelMap modelMap) {
//		modelMap.addAttribute("serie",new Serie());
//		modelMap.addAttribute("mode","new");
//		return "formSerie";
//	}
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
		List<Genre> genres = serieService.getAllGenres();
		Serie serie = new Serie();
		Genre genre = new Genre();
		genre = genres.get(0); // prendre le premier genre de la liste
		serie.setGenre(genre); //affedter une catégorie par défaut au produit pour éviter le pb avec une catégorie null
		modelMap.addAttribute("serie",serie);
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("genres", genres);
		return "formSerie";
	}

	@RequestMapping("/saveSerie")
	public String saveSerie(@Valid Serie serie, BindingResult bidingResult,
							 @RequestParam(name = "page", defaultValue = "0") int page,
							 @RequestParam(name = "size", defaultValue = "4") int size,ModelMap modelMap) {
		if (bidingResult.hasErrors()) {
			List<Genre> genres = serieService.getAllGenres();
			Genre genre = new Genre();
			modelMap.addAttribute("genres", genres);
			return "formSerie";
		}
		serieService.saveSerie(serie);
		return "redirect:/ListeSeries?page=" + (serieService.getAllSeriesParPage(page, size).getTotalPages() - 1) + "&size=" + size;
	
	}


	@RequestMapping("/ListeSeries")
	public String listeSeries(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
												 @RequestParam(name = "size", defaultValue = "4") int size) {
		Page<Serie> sers = serieService.getAllSeriesParPage(page, size);
		modelMap.addAttribute("series", sers);
		modelMap.addAttribute("pages", new int[sers.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeSeries";
	}

	@RequestMapping("/supprimerSerie")
	public String supprimerSerie(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "4") int size) {
		serieService.deleteSerieById(id);
		Page<Serie> sers = serieService.getAllSeriesParPage(page, size);
		modelMap.addAttribute("series", sers);
		modelMap.addAttribute("pages", new int[sers.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeSeries";
	}


	@RequestMapping("/modifierSerie")
	public String editerSerie(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Serie s= 	serieService.getSerie(id);
		List<Genre> genres = serieService.getAllGenres();
		modelMap.addAttribute("serie", s);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("genres", genres);
		return "formSerie";	
	}

	@RequestMapping("/updateSerie")
	public String updateSerie(@ModelAttribute("serie") Serie serie, @RequestParam("date") String date,
	                          ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
	                          @RequestParam(name = "size", defaultValue = "4") int size) throws ParseException {
	    // conversion de la date
	    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	    Date dateDiffusion = dateformat.parse(String.valueOf(date));
	    serie.setDateDiffusion(dateDiffusion);

	    serieService.updateSerie(serie);
	    return "redirect:/ListeSeries?page=0&size=" + size;
	}


}
