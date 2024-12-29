package com.example.demo.dto.model;

import com.example.demo.model.korisnici.Korisnik;

public class KorisnikDTO implements Comparable<KorisnikDTO>{
	
	private Integer id;
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private String telefon;
	private String drzava;
	private String grad;
	private String adresa;
	private boolean aktivan;
	private boolean promenjenaSifra;
	
	public KorisnikDTO() {
		super();
	}

	public KorisnikDTO(Korisnik korisnik) {
		super();
		this.id = korisnik.getId();
		this.email = korisnik.getEmail();
		this.lozinka = korisnik.getLozinka();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.telefon = korisnik.getTelefon();
		this.drzava = korisnik.getDrzava();
		this.grad = korisnik.getGrad();
		this.adresa = korisnik.getAdresa();
		this.aktivan = korisnik.isAktivan();
		this.promenjenaSifra = korisnik.isPromenjenaSifra();
	}

	@Override
	public int compareTo(KorisnikDTO k) {
		if (this.ime.compareTo(k.ime) != 0)
			return this.ime.compareTo(k.ime);
		return this.prezime.compareTo(k.prezime);
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

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public boolean isPromenjenaSifra() {
		return promenjenaSifra;
	}

	public void setPromenjenaSifra(boolean promenjenaSifra) {
		this.promenjenaSifra = promenjenaSifra;
	}
	
}
