package com.example.demo.model.korisnici;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.dto.pretraga.ObavezaDTO;
import com.example.demo.model.ostalo.Brisanje;
import com.example.demo.model.ostalo.Ocena;
import com.example.demo.model.ostalo.Ocenjivanje;
import com.example.demo.model.ostalo.Slobodnost;
import com.example.demo.model.ostalo.Zauzetost;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Klinika;
import com.example.demo.model.resursi.TipPosete;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.model.zahtevi.ZahtevPoseta;

@Entity
@DiscriminatorValue("lekar")
public class Lekar extends Zaposleni implements Ocenjivanje, Slobodnost, Brisanje{

	@ManyToOne
	@JoinColumn(name="specijalizacija")
	private TipPosete specijalizacija;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lekar_ocena",
    joinColumns = @JoinColumn(name = "lekar"),
    inverseJoinColumns = @JoinColumn(name = "ocena"))
	private Set<Ocena> ocene = new HashSet<>();
	@ManyToMany(mappedBy = "lekari", fetch = FetchType.EAGER)
	private Set<Poseta> posete = new HashSet<>();
	@OneToMany(mappedBy = "lekar", fetch = FetchType.EAGER)
	private Set<ZahtevPoseta> posetaZahtevi = new HashSet<>();
	@Column
	private Date poslednjaIzmena;
	@OneToOne
	@JoinColumn(name="zapocetaPoseta")
	private Poseta zapocetaPoseta;
	
	public Lekar() {
		super();
	}

	public Lekar(Integer id, String email, String lozinka, String ime, String prezime, 
			String telefon, String drzava, String grad, String adresa, 
			boolean aktivan, boolean promenjenaSifra, Date pocetnoVreme, Date krajnjeVreme,
			Klinika klinika, TipPosete specijalizacija, long version) {
		super(id, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, 
				aktivan, promenjenaSifra, pocetnoVreme, krajnjeVreme, klinika, version);
		this.specijalizacija = specijalizacija;
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
		Ocena o = new Ocena(ocena, pacijent, this.getId() + "L");
		this.ocene.add(o);
		return o;
		
	}
	
	@Override
	public boolean slobodan(Date pocetak, Date kraj) {
		
		if (!this.isAktivan())
			return false;
		
		GregorianCalendar gc = new GregorianCalendar();	
		
		gc.setTime(pocetak);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		Date pocetakDatum = gc.getTime();
		gc.setTime(kraj);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		Date krajDatum = gc.getTime();

		gc.setTime(pocetak);
		double pocetakVreme = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;
		gc.setTime(kraj);
		double krajVreme = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;
		gc.setTime(this.getPocetnoVreme());
		double smenaPocetak = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;
		gc.setTime(this.getKrajnjeVreme());
		double smenaKraj = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;		

		if (pocetakVreme < smenaPocetak)
			return false;
		
		if (krajVreme < smenaPocetak)
			return false;
		
		if (pocetakVreme > smenaKraj)
			return false;
		
		if (krajVreme > smenaKraj)
			return false;

		for (ZahtevOdmor z: this.getOdmorZahtevi()) {
			if (z.getPocetak().equals(pocetakDatum) || z.getKraj().equals(krajDatum))
				return false;
		}
		
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
	
	public boolean slobodanZahtev(Date pocetak, Date kraj, Integer id) {
		for (ZahtevPoseta p: this.posetaZahtevi) {
			if (!p.getId().equals(id)) {
				if ((pocetak.equals(p.pocetak()) || pocetak.after(p.pocetak()))
					&&  pocetak.before(p.kraj())) {
					return false; 
				}
				if ((kraj.after(p.pocetak())) 
					&& ( kraj.equals(p.kraj()) ||  kraj.before(p.kraj()))) {
					return false;
				}
			}
		}
		return true;
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
	
	@Override
	public boolean odmorPreklapanje(ZahtevOdmor zahtev) {

		for (Poseta p: this.posete) {

			if (!p.getStanje().equals(StanjePosete.OBAVLJENO)) {
				if ((zahtev.getPocetak().equals(p.pocetak()) || zahtev.getPocetak().after(p.pocetak()))
						&&  zahtev.getPocetak().before(p.kraj()))
					return true;
				if ((zahtev.getKraj().after(p.pocetak()))
						&& (zahtev.getKraj().equals(p.kraj()) ||  zahtev.getKraj().before(p.kraj())))
					return true;
				if ((zahtev.getPocetak().equals(p.pocetak()) || zahtev.getPocetak().before(p.pocetak())
						&& (zahtev.getKraj().equals(p.kraj()) || zahtev.getKraj().after(p.kraj()))))
					return true;
			}

		}
		
		for (ZahtevPoseta p: this.posetaZahtevi) {
			if ((zahtev.getPocetak().equals(p.pocetak()) || zahtev.getPocetak().after(p.pocetak()))
					&&  zahtev.getPocetak().before(p.kraj())) {
					return true; 
			}
			if ((zahtev.getKraj().after(p.pocetak())) 
					&& (zahtev.getKraj().equals(p.kraj()) || zahtev.getKraj().before(p.kraj()))) {
					return true;
			}
			if ((zahtev.getPocetak().equals(p.pocetak()) || zahtev.getPocetak().before(p.pocetak())
					&& (zahtev.getKraj().equals(p.kraj()) || zahtev.getKraj().after(p.kraj()))))
				return true;
		}
		
		return super.odmorPreklapanje(zahtev);
		
	}
	
	public List<ObavezaDTO> getObaveze() {
		List<ObavezaDTO> obaveze = new ArrayList<>();

		for (Poseta p : this.posete) {
			if (p.getStanje().equals(StanjePosete.ZAUZETO) || p.getStanje().equals(StanjePosete.SLOBODNO))
				obaveze.add(new ObavezaDTO(p));
		}

		return obaveze;
	}

	
	public List<Date> getSatnica(Date datum) {

		List<Date> satnica = new ArrayList<>();
		if (datum == null || !this.isAktivan())
			return satnica;
		
		for  (ZahtevOdmor z: this.getOdmorZahtevi()) {
			if ((z.getPocetak().equals(datum) || z.getPocetak().before(datum)) && (z.getKraj().equals(datum) || z.getKraj().after(datum)))
				return satnica;
		}
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(datum);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		Date compareDate = gc.getTime();
		
		List<Zauzetost> lista = new ArrayList<>();
		for (Poseta p: this.posete) {
			gc.setTime(p.pocetak());
			gc.set(Calendar.HOUR_OF_DAY, 0);
			gc.set(Calendar.MINUTE, 0);
			if (gc.getTime().equals(compareDate) && !p.getStanje().equals(StanjePosete.OBAVLJENO))
				lista.add(p);
		}
		
		for (ZahtevPoseta zp: this.posetaZahtevi) {
			gc.setTime(zp.pocetak());
			gc.set(Calendar.HOUR_OF_DAY, 0);
			gc.set(Calendar.MINUTE, 0);
			if (gc.getTime().equals(compareDate))
				lista.add(zp);
		}
		
		Collections.sort(lista);

		gc.setTime(this.getPocetnoVreme());
		int sati = gc.get(Calendar.HOUR_OF_DAY);
		int minute = gc.get(Calendar.MINUTE);
		gc.setTime(datum);
		gc.set(Calendar.HOUR_OF_DAY, sati);
		gc.set(Calendar.MINUTE, minute);
		Date start = gc.getTime();

		gc.setTime(this.getKrajnjeVreme());
		sati = gc.get(Calendar.HOUR_OF_DAY);
		minute = gc.get(Calendar.MINUTE);
		gc.setTime(datum);
		gc.set(Calendar.HOUR_OF_DAY, sati);
		gc.set(Calendar.MINUTE, minute);
		Date end = gc.getTime();

		Iterator<Zauzetost> iterator = lista.iterator();
		Zauzetost zauzetost = null;
		boolean sledeci = true;

		while (true) {
			
			gc.setTime(start);
			gc.add(Calendar.HOUR_OF_DAY, this.specijalizacija.getSati());
			gc.add(Calendar.MINUTE, this.specijalizacija.getMinute());
			Date temp1 = gc.getTime();

			if (temp1.after(end))
				break;
			
			if (sledeci) {
				
				if (!iterator.hasNext()) {
					
					while (temp1.before(end) || temp1.equals(end)) {
						
						satnica.add((Date) start.clone());
						gc.setTime(start);
						gc.add(Calendar.HOUR, this.specijalizacija.getSati());
						gc.add(Calendar.MINUTE, this.specijalizacija.getMinute());
						start = gc.getTime();
						gc.setTime(start);
						gc.add(Calendar.HOUR_OF_DAY, this.specijalizacija.getSati());
						gc.add(Calendar.MINUTE, this.specijalizacija.getMinute());
						temp1 = gc.getTime();

					}
					
					break;
				}
				
				zauzetost = iterator.next();
				
			}
			
			gc.setTime(zauzetost.pocetak());
			gc.add(Calendar.HOUR_OF_DAY, zauzetost.sati());
			gc.add(Calendar.MINUTE, zauzetost.minute());
			Date temp2 = gc.getTime();
			
			if ((start.after(zauzetost.pocetak()) || start.equals(zauzetost.pocetak())) && (start.before(temp2))) {
				gc.setTime(temp2);
				start = gc.getTime();
				sledeci = true;
			}
			
			else if ((temp1.after(zauzetost.pocetak())) && (temp1.before(temp2) || temp1.equals(temp2))) {
				gc.setTime(temp2);
				start = gc.getTime();
				sledeci = true;
			}
			
			else if (start.before(zauzetost.pocetak()) && temp1.after(temp2)) {
				gc.setTime(temp2);
				start = gc.getTime();
				sledeci = true;
			}
			
			else {
				satnica.add((Date) start.clone());
				gc.setTime(start);
				gc.add(Calendar.HOUR, this.specijalizacija.getSati());
				gc.add(Calendar.MINUTE, this.specijalizacija.getMinute());
				start = gc.getTime();
				sledeci = false;
			}	
		}

		return satnica;
		
	}
	
	public TipPosete getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(TipPosete specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
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

	public Poseta getZapocetaPoseta() {
		return zapocetaPoseta;
	}

	public void setZapocetaPoseta(Poseta zapocetaPoseta) {
		this.zapocetaPoseta = zapocetaPoseta;
	}
	
}
