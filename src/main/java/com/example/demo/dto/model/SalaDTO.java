package com.example.demo.dto.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.demo.dto.pretraga.PeriodDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Sala;

public class SalaDTO implements Comparable<SalaDTO>{

	private Integer id; 
	private String broj;
	private String naziv;
	private Integer klinika;
	private boolean aktivan;
	private Date prviSlobodan;
	private List<PeriodDTO> kalendar;
	
	public SalaDTO() {
		super();
	}
	
	public SalaDTO(Sala sala) {
		GregorianCalendar gc = new GregorianCalendar();
		this.id = sala.getId();
		this.broj = sala.getBroj();
		this.naziv = sala.getNaziv();
		this.klinika = sala.getKlinika().getId();
		this.aktivan = sala.isAktivan();
		this.kalendar = new ArrayList<>();
		for (Poseta p : sala.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO)) {
				gc.setTime(p.getDatum());
		        gc.add(Calendar.HOUR, p.getTipPosete().getSati());
		        gc.add(Calendar.MINUTE, p.getTipPosete().getMinute());
		        Date kraj = gc.getTime();
		        this.kalendar.add(new PeriodDTO(p.getDatum(), kraj));
			}
		}
		Collections.sort(this.kalendar);
	}

	@Override
	public int compareTo(SalaDTO s) {
		return this.broj.compareTo(s.broj);
	}
	
	public boolean proveriDatum(Date pocetak, Date kraj) {
		for (PeriodDTO period : this.kalendar) {
			if ((pocetak.after(period.getPocetak()) || pocetak.equals(period.getPocetak())) && pocetak.before(period.getKraj()))
				return false;
			if (kraj.after(period.getPocetak()) && (kraj.before(period.getKraj()) || kraj.equals(period.getKraj())))
				return false;
			if ((pocetak.before(period.getPocetak()) || pocetak.equals(period.getPocetak())) && (kraj.after(period.getKraj()) || kraj.equals(period.getKraj())))
				return false;
		}
		return true;
		
	}

	public void nadjiSlobodanTermin(String p, String k, Lekar lekar) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		GregorianCalendar pocetak = new GregorianCalendar();
		GregorianCalendar kraj = new GregorianCalendar();
		pocetak.setTime(f.parse(p));
		kraj.setTime(f.parse(k));
		while (!this.proveriDatum(pocetak.getTime(), kraj.getTime()) || !lekar.slobodan(pocetak.getTime(), kraj.getTime()) || !lekar.slobodanZahtev(pocetak.getTime(), kraj.getTime(), null)) {
			pocetak.add(Calendar.HOUR_OF_DAY, 1);
			kraj.add(Calendar.HOUR_OF_DAY, 1);
		}
		this.prviSlobodan = pocetak.getTime();
	}

	public void nadjiSlobodanTermin(String p, String k, List<Lekar> lekari) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		GregorianCalendar pocetak = new GregorianCalendar();
		GregorianCalendar kraj = new GregorianCalendar();
		pocetak.setTime(f.parse(p));
		kraj.setTime(f.parse(k));
		for (Lekar l : lekari) {
			while (!this.proveriDatum(pocetak.getTime(), kraj.getTime()) || !l.slobodan(pocetak.getTime(), kraj.getTime()) || !l.slobodanZahtev(pocetak.getTime(), kraj.getTime(), null)) {
				pocetak.add(Calendar.HOUR_OF_DAY, 1);
				kraj.add(Calendar.HOUR_OF_DAY, 1);
			}	
		}
		this.prviSlobodan = pocetak.getTime();		
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

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Date getPrviSlobodan() {
		return prviSlobodan;
	}

	public void setPrviSlobodan(Date prviSlobodan) {
		this.prviSlobodan = prviSlobodan;
	}

	public List<PeriodDTO> getKalendar() {
		return kalendar;
	}

	public void setKalendar(List<PeriodDTO> kalendar) {
		this.kalendar = kalendar;
	}
	
}
