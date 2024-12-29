package com.example.demo.model.zahtevi;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.resursi.Klinika;

@Entity
public class ZahtevOdmor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = false, nullable = false)
	private Date pocetak;
	@Column(unique = false, nullable = false)
	private Date kraj;
	@Column(unique = false, nullable = false)
	private boolean odobren;
	@ManyToOne
	@JoinColumn(name="zaposleni")
	private Zaposleni zaposleni;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	
	public ZahtevOdmor() {
		super();
	}
	
	public ZahtevOdmor(Integer id, Date pocetak, Date kraj, 
			boolean odobren, Zaposleni zaposleni, Klinika klinika) {
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.odobren = odobren;
		this.zaposleni = zaposleni;
		this.klinika = klinika;
	}

	public int getTrajanje() {
		return (int) ((this.kraj.getTime() - this.pocetak.getTime()) / 1000 / 60 / 60 / 24);
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
	public Zaposleni getZaposleni() {
		return zaposleni;
	}
	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}
	public Klinika getKlinika() {
		return klinika;
	}
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
}
