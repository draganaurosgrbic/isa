package com.example.demo.dto.model;

import java.util.List;

import com.example.demo.model.posete.Terapija;

public class TerapijaDTO implements Comparable<TerapijaDTO>{

	private Integer id;
	private String brojOsiguranika;
	private List<String> dijagnoze;
	private List<String> lekovi;
	
	public TerapijaDTO() {
		super();
	}

	public TerapijaDTO(Terapija terapija) {
		super();
		this.id = terapija.getId();
		this.brojOsiguranika = terapija.getIzvestaj().getPoseta().getKarton().getBrojOsiguranika();
		this.dijagnoze = terapija.getDijagnozeSifre();
		this.lekovi = terapija.getLekoviSifre();
	}

	@Override
	public int compareTo(TerapijaDTO o) {
		return this.brojOsiguranika.compareTo(o.brojOsiguranika);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrojOsiguranika() {
		return brojOsiguranika;
	}

	public void setBrojOsiguranika(String brojOsiguranika) {
		this.brojOsiguranika = brojOsiguranika;
	}

	public List<String> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<String> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public List<String> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<String> lekovi) {
		this.lekovi = lekovi;
	}
	
}