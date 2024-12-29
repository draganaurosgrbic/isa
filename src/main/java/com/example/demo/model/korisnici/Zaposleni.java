package com.example.demo.model.korisnici;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.model.resursi.Klinika;
import com.example.demo.model.zahtevi.ZahtevOdmor;

@Entity
public abstract class Zaposleni extends Korisnik{
	
	@Column(unique = false, nullable = true)
	private Date pocetnoVreme;
	@Column(unique = false, nullable = true)
	private Date krajnjeVreme;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	@OneToMany(mappedBy = "zaposleni", fetch = FetchType.EAGER)
	private Set<ZahtevOdmor> odmorZahtevi = new HashSet<>();

	public Zaposleni() {
		super();
	}

	public Zaposleni(Integer id, String email, String lozinka, String ime, String prezime, 
			String telefon, String drzava, String grad, String adresa, 
			boolean aktivan, boolean promenjenaSifra, Date pocetnoVreme, Date krajnjeVreme, 
			Klinika klinika, long version) {
		super(id, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, 
				aktivan, promenjenaSifra, version);
		this.pocetnoVreme = pocetnoVreme;
		this.krajnjeVreme = krajnjeVreme;
		this.klinika = klinika;
	}

	public boolean odmorPreklapanje(ZahtevOdmor zahtev) {
		
		for (ZahtevOdmor z: this.odmorZahtevi) {
			
			if ((zahtev.getPocetak().equals(z.getPocetak()) || zahtev.getPocetak().after(z.getPocetak()))
					&& zahtev.getPocetak().before(z.getKraj()))
				return true;

			if (zahtev.getKraj().after(z.getPocetak()) && (zahtev.getKraj().equals(z.getKraj()) ||
					zahtev.getKraj().before(z.getKraj())))
				return true;
			
			if ((zahtev.getPocetak().equals(z.getPocetak()) || zahtev.getPocetak().before(z.getPocetak())) &&
					(zahtev.getKraj().equals(z.getKraj()) || zahtev.getKraj().after(z.getKraj())))
				return true;
			
		}
		
		return false;
		
	}
	
	public Date getPocetnoVreme() {
		return pocetnoVreme;
	}

	public void setPocetnoVreme(Date pocetnoVreme) {
		this.pocetnoVreme = pocetnoVreme;
	}

	public Date getKrajnjeVreme() {
		return krajnjeVreme;
	}

	public void setKrajnjeVreme(Date krajnjeVreme) {
		this.krajnjeVreme = krajnjeVreme;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public Set<ZahtevOdmor> getOdmorZahtevi() {
		return odmorZahtevi;
	}

	public void setOdmorZahtevi(Set<ZahtevOdmor> odmorZahtevi) {
		this.odmorZahtevi = odmorZahtevi;
	}

}
