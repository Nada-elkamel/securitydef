package com.nada.series.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Serie {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSerie;
	
	@NotNull
	@Size (min = 4,max = 15)
	private String nomSerie;
	
	@NotNull
	@Size (min = 4,max = 15)
	private String langue;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private Date dateDiffusion;
	
	@ManyToOne
	private Genre genre;
	
	public Serie() {
		super();
	}

	public Serie(String nomSerie, String langue, Date dateDiffusion,Genre genre) {
		this.nomSerie = nomSerie;
		this.langue = langue;
		this.dateDiffusion = dateDiffusion;
		this.genre=genre;
	}

	public Long getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Long idSerie) {
		this.idSerie = idSerie;
	}

	public String getNomSerie() {
		return nomSerie;
	}

	public void setNomSerie(String nomSerie) {
		this.nomSerie = nomSerie;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public Date getDateDiffusion() {
		return dateDiffusion;
	}

	public void setDateDiffusion(Date dateDiffusion) {
		this.dateDiffusion = dateDiffusion;
	}

	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Serie [idSerie=" + idSerie + ", nomSerie=" + nomSerie + ", langue=" + langue + ", dateDiffusion="
				+ dateDiffusion + "]";
	}
	
	
	
	
}
