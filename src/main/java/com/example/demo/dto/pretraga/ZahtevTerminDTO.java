package com.example.demo.dto.pretraga;

import java.util.Date;

import com.example.demo.model.zahtevi.ZahtevPoseta;

public class ZahtevTerminDTO implements Comparable<ZahtevTerminDTO>{
	
	private Integer id;
	private Date datum;
	private String tipPosete;
	private String nazivPosete;
	private String lekar;
	private double trajanje;
	private double cena;
	
	public ZahtevTerminDTO() {
		super();
	}
	
	public ZahtevTerminDTO(ZahtevPoseta zahtev) {
		super();
		this.id = zahtev.getId();
		this.datum = zahtev.getDatum();
		this.tipPosete = zahtev.getTipPosete().isPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = zahtev.getTipPosete().getNaziv();
		this.lekar = zahtev.getLekar().getIme() + " " + zahtev.getLekar().getPrezime();
		this.trajanje = zahtev.getTipPosete().getSati() + zahtev.getTipPosete().getMinute() / 60.0;
		this.cena = zahtev.getTipPosete().getCena();
	}
	
	@Override
	public int compareTo(ZahtevTerminDTO z) {
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

	public String getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(String tipPosete) {
		this.tipPosete = tipPosete;
	}

	public String getNazivPosete() {
		return nazivPosete;
	}

	public void setNazivPosete(String nazivPosete) {
		this.nazivPosete = nazivPosete;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

}
