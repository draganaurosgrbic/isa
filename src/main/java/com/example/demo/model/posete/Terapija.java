package com.example.demo.model.posete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.example.demo.model.korisnici.Sestra;
import com.example.demo.model.resursi.Dijagnoza;
import com.example.demo.model.resursi.Lek;

@Entity
public class Terapija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name="izvestaj")
	private Izvestaj izvestaj;
	@ManyToOne
	@JoinColumn(name="sestra")
	private Sestra sestra;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "terapija_lek",
    joinColumns = @JoinColumn(name = "terapija"),
    inverseJoinColumns = @JoinColumn(name = "lek"))
	private Set<Lek> lekovi = new HashSet<>();
	@Version
	private long version;
	
	public Terapija() {
		super();
	}

	public Terapija(Integer id, Izvestaj izvestaj, Sestra sestra) {
		super();
		this.id = id;
		this.izvestaj = izvestaj;
		this.sestra = sestra;
	}

	public List<String> getDijagnozeSifre() {
		List<String> sifre = new ArrayList<>();
		for (Dijagnoza d : this.izvestaj.getDijagnoze())
			sifre.add(d.getSifra());
		return sifre;
	}
	
	public List<String> getLekoviSifre() {
		List<String> sifre = new ArrayList<>();
		for (Lek l : this.lekovi)
			sifre.add(l.getSifra());
		return sifre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Izvestaj getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(Izvestaj izvestaj) {
		this.izvestaj = izvestaj;
	}

	public Sestra getSestra() {
		return sestra;
	}

	public void setSestra(Sestra sestra) {
		this.sestra = sestra;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
