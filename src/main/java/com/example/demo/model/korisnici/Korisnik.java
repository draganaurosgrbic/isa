package com.example.demo.model.korisnici;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tip", discriminatorType = DiscriminatorType.STRING)
public abstract class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = false, nullable = false)
	private String lozinka;
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
	@Column(unique = false, nullable = false)
	private boolean aktivan;
	@Column(unique = false, nullable = false)
	private boolean promenjenaSifra;
	@Version
	private long version;
	
	public Korisnik() {
		super();
	}

	public Korisnik(Integer id, String email, String lozinka, String ime, String prezime, 
			String telefon, String drzava, String grad, String adresa, 
			boolean aktivan, boolean promenjenaSifra, long version) {
		super();
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.drzava = drzava;
		this.grad = grad;
		this.adresa = adresa;
		this.aktivan = aktivan;
		this.promenjenaSifra = promenjenaSifra;
		this.version = version;
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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
