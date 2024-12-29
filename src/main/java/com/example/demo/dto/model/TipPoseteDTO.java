package com.example.demo.dto.model;

import com.example.demo.model.resursi.TipPosete;

public class TipPoseteDTO implements Comparable<TipPoseteDTO>{
	
	private Integer id;
	private boolean pregled;
	private String naziv;
	private double cena;
	private Integer sati;
	private Integer minute;
	private Integer klinika;
	private boolean aktivan;
	
	public TipPoseteDTO() {
		super();
	}

	public TipPoseteDTO(TipPosete tipPosete) {
		super();
		this.id = tipPosete.getId();
		this.pregled = tipPosete.isPregled();
		this.naziv = tipPosete.getNaziv();
		this.cena = tipPosete.getCena();
		this.sati = tipPosete.getSati();
		this.minute = tipPosete.getMinute();
		this.klinika = tipPosete.getKlinika().getId();
		this.aktivan = tipPosete.isAktivan();
	}

	@Override
	public int compareTo(TipPoseteDTO t) {
		return this.naziv.compareTo(t.naziv);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Integer getSati() {
		return sati;
	}

	public void setSati(Integer sati) {
		this.sati = sati;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

}
