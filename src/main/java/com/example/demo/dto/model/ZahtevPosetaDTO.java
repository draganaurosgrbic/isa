package com.example.demo.dto.model;

import java.util.Date;

import com.example.demo.model.zahtevi.ZahtevPoseta;

public class ZahtevPosetaDTO implements Comparable<ZahtevPosetaDTO>{
	
	private Integer id;
	private Date datum;
	private String vreme;
	private Integer karton;
	private Integer lekar;
	private Integer tipPosete;
	private Integer klinika;
	
	public ZahtevPosetaDTO() {
		super();
	}

	public ZahtevPosetaDTO(ZahtevPoseta zahtev) {
		super();
		this.id = zahtev.getId();
		this.datum = zahtev.getDatum();
		this.karton = zahtev.getKarton().getId();
		this.lekar = zahtev.getLekar().getId();
		this.tipPosete = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getId() : null;
		this.klinika = zahtev.getKlinika().getId();
	}

	@Override
	public int compareTo(ZahtevPosetaDTO z) {
		return this.datum.compareTo(z.datum);
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

	public Integer getKarton() {
		return karton;
	}

	public void setKarton(Integer karton) {
		this.karton = karton;
	}

	public Integer getLekar() {
		return lekar;
	}

	public void setLekar(Integer lekar) {
		this.lekar = lekar;
	}

	public Integer getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(Integer tipPosete) {
		this.tipPosete = tipPosete;
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}
	
}