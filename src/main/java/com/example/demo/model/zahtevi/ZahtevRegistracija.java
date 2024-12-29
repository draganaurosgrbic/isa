package com.example.demo.model.zahtevi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ZahtevRegistracija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = false, nullable = false)
	private String email;
	@Column(unique = false, nullable = false)
	private String lozinka;
	@Column(unique = false, nullable = false)
	private String brojOsiguranika;
	@Column(unique = false, nullable = false)
	private String ime;
	@Column(unique = false, nullable = false)
	private String prezime;
	@Column(unique = false, nullable = false)
	private String telefon;
	@Column(unique = false, nullable = false)
	private String drzava;
	@Column(unique = false, nullable = false)
	private String grad;
	@Column(unique = false, nullable = false)
	private String adresa;
	
	public ZahtevRegistracija() {
		super();
	}

	public ZahtevRegistracija(Integer id, String email, String lozinka, String ime, String prezime, 
			String telefon, String brojOsiguranika, String drzava, String grad, String adresa) {
		super();
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.brojOsiguranika = brojOsiguranika;
		this.drzava = drzava;
		this.grad = grad;
		this.adresa = adresa;
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
