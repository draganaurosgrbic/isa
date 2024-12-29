package com.example.demo.dto.unos;

import java.util.Date;

public class PredefinisanaPosetaDTO {
	
	private Integer id;
	private Date datum;
	private String vreme;
	private Integer tipPregleda;
	private Integer sala;
	private Integer lekar;
	private Double popust;
	
	public PredefinisanaPosetaDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public Integer getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(Integer tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public Integer getLekar() {
		return lekar;
	}

	public void setLekar(Integer lekar) {
		this.lekar = lekar;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}
	
}
