package com.example.demo.model.resursi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.ostalo.Ocena;
import com.example.demo.model.ostalo.Ocenjivanje;
import com.example.demo.model.ostalo.Profitiranje;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.model.zahtevi.ZahtevPoseta;

@Entity
public class Klinika implements Ocenjivanje, Profitiranje{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String naziv;
	@Column(unique = false, nullable = true)
	private String opis;
	@Column(unique = false, nullable = false)
	private String adresa;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "klinika_ocena",
    joinColumns = @JoinColumn(name = "klinika"),
    inverseJoinColumns = @JoinColumn(name = "ocena"))
	private Set<Ocena> ocene = new HashSet<>();
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<Zaposleni> zaposleni = new HashSet<>();
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<TipPosete> tipoviPoseta = new HashSet<>();
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<Sala> sale = new HashSet<>();
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<ZahtevPoseta> posetaZahtevi = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy="klinika")
	private Set<ZahtevOdmor> odmorZahtevi = new HashSet<>();
	
	public Klinika() {
		super();
	}

	public Klinika(Integer id, String naziv, String opis, String adresa) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.adresa = adresa;
	}
	
	@Override
	public double prosecnaOcena() {
		if (this.ocene.isEmpty())
			return 0.0;
		double suma = 0.0;
		for (Ocena o : this.ocene)
			suma += o.getVrednost();
		return suma/this.ocene.size();
	}
	
	@Override
	public Ocena refreshOcena(Pacijent pacijent, int ocena) {

		for (Ocena o: this.ocene) {
			if (o.getPacijent().getId().equals(pacijent.getId())) {
				o.setVrednost(ocena);
				return o;
			}
		}
		Ocena o = new Ocena(ocena, pacijent, this.id + "K");
		this.ocene.add(o);
		return o;
		
	}
	
	@Override
	public double getProfit(Date pocetak, Date kraj) {
		double suma = 0.0;
		for (Poseta p: this.getPosete())
			suma += p.getProfit(pocetak, kraj);
		return suma;
	}
	
	public Map<String, Integer> podaciGraf(String parametar) {
		Date danas = new Date();
		GregorianCalendar gc = new GregorianCalendar();	
		gc.setTime(danas);
		
		if (parametar.equals("nedeljni"))
			gc.add(Calendar.DAY_OF_WEEK, -7);

		if (parametar.equals("mesecni"))
			gc.add(Calendar.DAY_OF_MONTH, -30);

		if (parametar.equals("godisnji")) 
			gc.add(Calendar.DAY_OF_YEAR, -365);

		Map<String, Integer> podaci = new HashMap<>();
		for (Poseta p : this.getPosete()) {
			if ((p.getDatum().after(gc.getTime()) || p.getDatum().equals(gc.getTime())) && (p.getDatum().before(danas) || p.getDatum().equals(danas))) {
				if (podaci.containsKey(p.getTipPosete().getNaziv())) 
					podaci.computeIfPresent(p.getTipPosete().getNaziv(), (k, v) -> v + 1);
				else
					podaci.put(p.getTipPosete().getNaziv(), 1);
			}
		}
		return podaci;
	}
	
	public List<Poseta> getPosete(){
		
		List<Poseta> posete = new ArrayList<>();
		for (Sala s: this.sale) {
			for (Poseta p: s.getPosete())
				posete.add(p);
		}
		return posete;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Set<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Set<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Set<TipPosete> getTipoviPoseta() {
		return tipoviPoseta;
	}

	public void setTipoviPoseta(Set<TipPosete> tipoviPoseta) {
		this.tipoviPoseta = tipoviPoseta;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public Set<ZahtevPoseta> getPosetaZahtevi() {
		return posetaZahtevi;
	}

	public void setPosetaZahtevi(Set<ZahtevPoseta> posetaZahtevi) {
		this.posetaZahtevi = posetaZahtevi;
	}

	public Set<ZahtevOdmor> getOdmorZahtevi() {
		return odmorZahtevi;
	}

	public void setOdmorZahtevi(Set<ZahtevOdmor> odmorZahtevi) {
		this.odmorZahtevi = odmorZahtevi;
	}
	
}
