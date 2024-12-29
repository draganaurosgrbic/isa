package com.example.demo.dto.model;

import com.example.demo.model.zahtevi.ZahtevRegistracija;

public class ZahtevRegistracijaDTO implements Comparable<ZahtevRegistracijaDTO>{
	
	private Integer id;
	private String email;
	private String lozinka;
	private String brojOsiguranika;
	private String ime;
	private String prezime;
	private String telefon;
	private String drzava;
	private String grad;
	private String adresa;
	
	public ZahtevRegistracijaDTO() {
		super();
	}

	public ZahtevRegistracijaDTO(ZahtevRegistracija zahtev) {
		super();
		this.id = zahtev.getId();
		this.email = zahtev.getEmail();
		this.lozinka = zahtev.getLozinka();
		this.brojOsiguranika = zahtev.getBrojOsiguranika();
		this.ime = zahtev.getIme();
		this.prezime = zahtev.getPrezime();
		this.telefon = zahtev.getTelefon();
		this.drzava = zahtev.getDrzava();
		this.grad = zahtev.getGrad();
		this.adresa = zahtev.getAdresa();
	}

	@Override
	public int compareTo(ZahtevRegistracijaDTO z) {
		return this.brojOsiguranika.compareTo(z.brojOsiguranika);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getBrojOsiguranika() {
		return brojOsiguranika;
	}

	public void setBrojOsiguranika(String brojOsiguranika) {
		this.brojOsiguranika = brojOsiguranika;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
