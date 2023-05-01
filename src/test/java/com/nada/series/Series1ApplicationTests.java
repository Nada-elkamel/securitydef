package com.nada.series;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.nada.series.entities.Genre;
import com.nada.series.entities.Serie;
import com.nada.series.repos.SerieRepository;
import com.nada.series.service.SerieService;

@SpringBootTest
class Series1ApplicationTests {

		@Autowired
		private SerieRepository serieRepository;
		
		@Autowired
		private SerieService serieService;
		
		@Test
		public void testCreateSerie() {
		Serie serie = new Serie("The legend of the blue sea","Turc",new Date());
		serieRepository.save(serie);
		
		}
		
		@Test
		public void testFindSerie()
		{
		Serie s = serieRepository.findById(1L).get();
		System.out.println(s);
		}
		@Test
		public void testUpdateProduit()
		{
		Serie s = serieRepository.findById(1L).get();
		s.setLangue("cooréan");
		serieRepository.save(s);
		}

		@Test
		public void testDeleteSerie()
		{
			serieRepository.deleteById(1L);;
		}

		@Test
		public void testListerTousSeries()
		{
		List<Serie> ser = serieRepository.findAll();
		for (Serie s : ser)
		{
		System.out.println(s);
		}
		}
		
//		@Test
//		public void testFindByNomSerieContains()
//		{
//		Page<Serie> sers = serieService.getAllSeriesParPage(0,2);
//		System.out.println(sers.getSize());
//		System.out.println(sers.getTotalElements());
//		System.out.println(sers.getTotalPages());
//		sers.getContent().forEach(s -> {System.out.println(s.toString());
//		 });
//		}
		
		@Test
		public void testFindByNomSerie()
		{
			List<Serie> serie = serieRepository.findByNomSerie("ah nerede");
			for (Serie s : serie)
			{
			System.out.println(s);
			}
			}

//		@Test
//		public void testFindByNomSerieContains ()
//		{
//		List<Serie> serie=serieRepository.findByNomSerieContains("ah nerede");
//		for (Serie s : serie)
//		{
//		System.out.println(s);
//		} }
//		
		@Test
		public void testFindByNomSerieContains() {
		    List<Serie> series = serieRepository.findByNomSerieContains("a");
		    for (Serie s : series) {
		        System.out.println(s);
		    }

		    Page<Serie> sers = serieService.getAllSeriesParPage(0, 2);
		    System.out.println(sers.getSize());
		    System.out.println(sers.getTotalElements());
		    System.out.println(sers.getTotalPages());
		    sers.getContent().forEach(s -> {
		        System.out.println(s.toString());
		    });
		}


		@Test
		public void testfindByNomLangue()
		{
		List<Serie> serie = serieRepository.findByNomLangue("ah nerede", "Turc");
		for (Serie s : serie)
		{
		System.out.println(s);
		}
		}


		@Test
		public void testfindByGenre()
		{
		Genre genre = new Genre();
		genre.setIdGenre(3L);
		List<Serie> serie = serieRepository.findByGenre(genre);
		for (Serie s : serie)
		{
		System.out.println(s);
		}
		}
		
		@Test
		public void findByGenreIdGenre()
		{
		List<Serie> serie = serieRepository.findByGenreIdGenre(1L);
		for (Serie s : serie)
		{
		System.out.println(s);
		}
		 }
		
		@Test
		public void testfindByOrderByNomSerieAsc()
		{
		List<Serie> serie =
		serieRepository.findByOrderByNomSerieAsc();
		for (Serie s : serie)
		{
		System.out.println(s);
		}
		}
		
		@Test
		public void testTrierSeriesNomsLangue()
		{
		List<Serie> serie = serieRepository.trierSeriesNomsLangue();
		for (Serie s : serie)
		{
		System.out.println(s);
		}
		}



}
