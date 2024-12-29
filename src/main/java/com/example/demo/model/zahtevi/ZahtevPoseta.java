package com.example.demo.model.zahtevi;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.ostalo.Zauzetost;
import com.example.demo.model.posete.Karton;
import com.example.demo.model.resursi.Klinika;
import com.example.demo.model.resursi.TipPosete;

@Entity
public class ZahtevPoseta implements Zauzetost{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = false, nullable = false)
	private Date datum;
	@ManyToOne
	@JoinColumn(name="karton")
	private Karton karton;
	@ManyToOne
	@JoinColumn(name="lekar")
	private Lekar lekar;
	@ManyToOne
	@JoinColumn(name="tipPosete")
	private TipPosete tipPosete;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;

	public ZahtevPoseta() {
		super();
	}

	public ZahtevPoseta(Integer id, Date datum, Karton karton, 
			Lekar lekar, TipPosete tipPosete, Klinika klinika) {
		super();
		this.id = id;
		this.datum = datum;
		this.karton = karton;
		this.lekar = lekar;
		this.tipPosete = tipPosete;
		this.klinika = klinika;
	}

	@Override
	public int compareTo(Zauzetost o) {
		return this.datum.compareTo(o.pocetak());
	}

	@Override
	public Date pocetak() {
		return this.datum;
	}

	@Override
	public int sati() {
		return this.tipPosete.getSati();
	}

	@Override
	public int minute() {
		return this.tipPosete.getMinute();
	}

	@Override
	public Date kraj() {
		GregorianCalendar gs = new GregorianCalendar();
		gs.setTime(this.pocetak());
		gs.add(Calendar.HOUR_OF_DAY, this.sati());
		gs.add(Calendar.MINUTE, this.minute());
		return gs.getTime();
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

	public Karton getKarton() {
		return karton;
	}

	public void setKarton(Karton karton) {
		this.karton = karton;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public TipPosete getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(TipPosete tipPosete) {
		this.tipPosete = tipPosete;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
}