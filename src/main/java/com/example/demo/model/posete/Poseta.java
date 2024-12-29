package com.example.demo.model.posete;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.ostalo.Profitiranje;
import com.example.demo.model.ostalo.Zauzetost;
import com.example.demo.model.resursi.Sala;
import com.example.demo.model.resursi.TipPosete;

@Entity
public class Poseta implements Zauzetost, Profitiranje{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = false, nullable = false)
	private StanjePosete stanje;
	@Column(unique = false, nullable = false)
	private Date datum;
	@Column(unique = false, nullable = true)
	private Double popust;
	@ManyToOne
	@JoinColumn(name="tipPosete")
	private TipPosete tipPosete;
	@ManyToOne
	@JoinColumn(name="sala")
	private Sala sala;
	@ManyToOne
	@JoinColumn(name="karton")
	private Karton karton;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lekar_poseta",
    joinColumns = @JoinColumn(name = "poseta"),
    inverseJoinColumns = @JoinColumn(name = "lekar"))
	private Set<Lekar> lekari = new HashSet<>();
	@OneToOne
	@JoinColumn(name="izvestaj")
	private Izvestaj izvestaj;
	@Version
	private long version;
	
	public Poseta() {
		super();
	}
	
	public Poseta(StanjePosete stanje, Date datum, Double popust, 
			TipPosete tipPosete, Sala sala, Karton karton, Lekar lekar) {
		super();
		this.stanje = stanje;
		this.datum = datum;
		this.popust = popust;
		this.tipPosete = tipPosete;
		this.sala = sala;
		this.karton = karton;
		this.lekari.add(lekar);
	}
	
	public Poseta(StanjePosete stanje, Date datum, Double popust, 
			TipPosete tipPosete, Sala sala, Karton karton, Set<Lekar> lekari) {
		super();
		this.stanje = stanje;
		this.datum = datum;
		this.popust = popust;
		this.tipPosete = tipPosete;
		this.sala = sala;
		this.karton = karton;
		this.lekari = lekari;
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
	
	@Override
	public double getProfit(Date pocetak, Date kraj) {
		if (!this.stanje.equals(StanjePosete.OBAVLJENO))
			return 0.0;
		if (!(this.pocetak().after(pocetak) && this.pocetak().before(kraj)))
			return 0.0;
		if (!(this.kraj().after(pocetak) && this.kraj().before(kraj)))
			return 0.0;
		double suma = this.tipPosete.getCena();
		if (this.popust != null)
			suma *= (1 - this.popust);
		return suma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StanjePosete getStanje() {
		return stanje;
	}

	public void setStanje(StanjePosete stanje) {
		this.stanje = stanje;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}

	public TipPosete getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(TipPosete tipPosete) {
		this.tipPosete = tipPosete;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Karton getKarton() {
		return karton;
	}

	public void setKarton(Karton karton) {
		this.karton = karton;
	}

	public Set<Lekar> getLekari() {
		return lekari;
	}

	public void setLekari(Set<Lekar> lekari) {
		this.lekari = lekari;
	}

	public Izvestaj getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(Izvestaj izvestaj) {
		this.izvestaj = izvestaj;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
