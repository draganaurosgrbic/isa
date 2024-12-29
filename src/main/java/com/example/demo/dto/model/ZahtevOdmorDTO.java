package com.example.demo.dto.model;

import java.util.Date;

import org.hibernate.Hibernate;

import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.zahtevi.ZahtevOdmor;

public class ZahtevOdmorDTO implements Comparable<ZahtevOdmorDTO>{

	private Integer id;
	private Date pocetak;
	private Date kraj;
	private boolean odobren;
	private Integer zaposleni;
	private Integer klinika;
	private String ime;
	private String prezime;
	private String profesija;
	
	public ZahtevOdmorDTO() {
		super();
	}
	
	public ZahtevOdmorDTO(ZahtevOdmor zahtev) {
		super();
		this.id = zahtev.getId();
		this.pocetak = zahtev.getPocetak();
		this.kraj = zahtev.getKraj();
		this.odobren = zahtev.isOdobren();
		this.zaposleni = zahtev.getZaposleni().getId();
		this.klinika = zahtev.getKlinika().getId();
		this.ime = zahtev.getZaposleni().getIme();
		this.prezime = zahtev.getZaposleni().getPrezime();
		this.profesija = ((Zaposleni) Hibernate.unproxy(zahtev.getZaposleni())).getClass().getSimpleName();
	}
	
	@Override
	public int compareTo(ZahtevOdmorDTO z) {
		return this.pocetak.compareTo(z.pocetak);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public Integer getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Integer zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getProfesija() {
		return profesija;
	}

	public void setProfesija(String profesija) {
		this.profesija = profesija;
	}

}
