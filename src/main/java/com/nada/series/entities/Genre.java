package com.nada.series.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idGenre;
	
	private String nomGenre;
	private String descriptionGenre;
	
	@JsonIgnore
	@OneToMany(mappedBy= "genre")
	private List<Serie> series;
	
	
}
