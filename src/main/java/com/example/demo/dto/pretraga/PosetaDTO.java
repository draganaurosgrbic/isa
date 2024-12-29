package com.example.demo.dto.pretraga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Poseta;

public class PosetaDTO implements Comparable<PosetaDTO>{
	
	private Integer id;
	private Date datum;
	private Double popust;
	private double cena;
	private String naziv;
	private double trajanje;
	private String sala;
	private List<String> lekari;
	
	public PosetaDTO() {
		super();
	}

	public PosetaDTO(Poseta poseta) {
		super();
		this.id = poseta.getId();
		this.datum = poseta.getDatum();
		this.popust = poseta.getPopust();
		this.cena = poseta.getTipPosete().getCena();
		this.naziv = poseta.getTipPosete().getNaziv();
		this.trajanje = poseta.getTipPosete().getSati() + poseta.getTipPosete().getMinute() / 60.0;
		this.sala = poseta.getSala().getBroj() + " " + poseta.getSala().getNaziv();
		this.lekari = new ArrayList<>();
		for (Lekar l: poseta.getLekari())
			this.lekari.add(l.getIme() + " " + l.getPrezime());
		Collections.sort(this.lekari);

	}

	@Override
	public int compareTo(PosetaDTO p) {
		return this.datum.compareTo(p.datum);
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

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public List<String> getLekari() {
		return lekari;
	}

	public void setLekari(List<String> lekari) {
		this.lekari = lekari;
	}
	
}
