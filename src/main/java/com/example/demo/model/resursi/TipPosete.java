package com.example.demo.model.resursi;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.example.demo.model.ostalo.Brisanje;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.zahtevi.ZahtevPoseta;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"klinika", "naziv"})})
public class TipPosete implements Brisanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private boolean pregled;
	@Column(unique = false, nullable = false)
	private String naziv;
	@Column(unique = false, nullable = false)
	private double cena;
	@Column(unique = false, nullable = false)
	private int sati;
	@Column(unique = false, nullable = false)
	private int minute;
	@Column
	private boolean aktivan;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	@OneToMany(mappedBy = "tipPosete", fetch = FetchType.EAGER)
	private Set<Poseta> posete = new HashSet<>();
	@OneToMany(mappedBy = "tipPosete", fetch = FetchType.EAGER)
	private Set<ZahtevPoseta> posetaZahtevi = new HashSet<>();
	@Column
	private Date poslednjaIzmena;	
	@Version
	private long version;
	
	public TipPosete() {
		super();
	}

	public TipPosete(Integer id, boolean pregled, String naziv, double cena, 
			int sati, int minute, Klinika klinika, boolean aktivan,long version) {
		super();
		this.id = id;
		this.pregled = pregled;
		this.naziv = naziv;
		this.cena = cena;
		this.sati = sati;
		this.minute = minute;
		this.klinika = klinika;
		this.aktivan = aktivan;
		this.version = version;
	}

	@Override
	public boolean mozeBrisanje() {
		if (!this.posetaZahtevi.isEmpty())
			return false;
		for (Poseta p: this.posete) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO))
				return false;
		}
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getSati() {
		return sati;
	}

	public void setSati(int sati) {
		this.sati = sati;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public Set<Poseta> getPosete() {
		return posete;
	}

	public void setPosete(Set<Poseta> posete) {
		this.posete = posete;
	}

	public Set<ZahtevPoseta> getPosetaZahtevi() {
		return posetaZahtevi;
	}

	public void setPosetaZahtevi(Set<ZahtevPoseta> posetaZahtevi) {
		this.posetaZahtevi = posetaZahtevi;
	}

	
	public Date getPoslednjaIzmena() {
		return poslednjaIzmena;
	}

	public void setPoslednjaIzmena(Date poslednjaIzmena) {
		this.poslednjaIzmena = poslednjaIzmena;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	
}
