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
import javax.persistence.Version;

import com.example.demo.model.ostalo.Brisanje;
import com.example.demo.model.ostalo.Slobodnost;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;

@Entity
public class Sala implements Slobodnost, Brisanje{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String broj;
	@Column(unique = false, nullable = false)
	private String naziv;
	@Column(unique = false, nullable = false)
	private boolean aktivan;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	@OneToMany(mappedBy = "sala", fetch = FetchType.EAGER)
	private Set<Poseta> posete = new HashSet<>();
	@Column
	private Date poslednjaIzmena;
	@Version
	private long version;
	
	public Sala() {
		super();
	}

	public Sala(Integer id, String broj, String naziv, Klinika klinika, boolean aktivan, long version) {
		super();
		this.id = id;
		this.broj = broj;
		this.naziv = naziv;
		this.klinika = klinika;
		this.aktivan = aktivan;
		this.version = version;
	}

	@Override
	public boolean slobodan(Date pocetak, Date kraj) {
		
		if (!this.aktivan)
			return false;

		for (Poseta p: this.posete) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO)) {
				if ((pocetak.equals(p.pocetak()) || pocetak.after(p.pocetak()))
						&&  pocetak.before(p.kraj()))
					return false;
				if ((kraj.after(p.pocetak()))
						&& ( kraj.equals(p.kraj()) ||  kraj.before(p.kraj())))
					return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean mozeBrisanje() {
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

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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
