package com.example.demo.dto.model;

import com.example.demo.model.resursi.Lek;

public class LekDTO implements Comparable<LekDTO>{

	private Integer id;
	private String sifra;
	private String naziv;
	
	public LekDTO() {
		super();
	}

	public LekDTO(Lek lek) {
		super();
		this.id = lek.getId();
		this.sifra = lek.getSifra();
		this.naziv = lek.getNaziv();
	}

	@Override
	public int compareTo(LekDTO l) {
		return this.sifra.compareTo(l.sifra);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
}