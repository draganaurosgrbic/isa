package com.example.demo.dto.pretraga;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.demo.dto.model.IzvestajDTO;
import com.example.demo.dto.model.KartonDTO;
import com.example.demo.dto.model.PacijentDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.korisnici.Sestra;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;

public class PacijentPretragaDTO extends PacijentDTO {
	
	private KartonDTO kartonObj;
	private List<IzvestajDTO> stariIzvestaji;
	private Integer zakazanaPoseta;
	
	public PacijentPretragaDTO(Pacijent pacijent, Lekar lekar) {
		super(pacijent);
		this.kartonObj = new KartonDTO(pacijent.getKarton());
		this.stariIzvestaji = new ArrayList<>();
		for (Poseta p: pacijent.getKarton().getPosete()) {
			for (Lekar l: p.getLekari()) {
				if (l.getId().equals(lekar.getId()) && 
						p.getStanje().equals(StanjePosete.OBAVLJENO))
					this.stariIzvestaji.add(new IzvestajDTO(p.getIzvestaj()));
			}
		}
		
		Collections.sort(this.stariIzvestaji);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(Calendar.MINUTE, -10);
		Date lowLimit = gc.getTime();
		gc.setTime(new Date());
		gc.add(Calendar.MINUTE, 20);
		Date highLimit = gc.getTime();
		for (Poseta p: pacijent.getKarton().getPosete()) {
			if (p.getStanje().equals(StanjePosete.ZAUZETO) && 
					p.getDatum().after(lowLimit) && p.getDatum().before(highLimit))
				this.zakazanaPoseta = p.getId();
		}
	}

	public PacijentPretragaDTO(Pacijent pacijent, Sestra sestra) {
		super(pacijent);
		this.kartonObj = new KartonDTO(pacijent.getKarton());
		this.stariIzvestaji = new ArrayList<>();
		
		for (Poseta p: pacijent.getKarton().getPosete()) {
			if (p.getSala().getKlinika().equals(sestra.getKlinika()) && 
					p.getStanje().equals(StanjePosete.OBAVLJENO))
				this.stariIzvestaji.add(new IzvestajDTO(p.getIzvestaj()));
		}
		
		Collections.sort(this.stariIzvestaji);
	}
	
	public KartonDTO getKartonObj() {
		return kartonObj;
	}

	public void setKartonObj(KartonDTO kartonObj) {
		this.kartonObj = kartonObj;
	}

	public List<IzvestajDTO> getStariIzvestaji() {
		return stariIzvestaji;
	}

	public void setStariIzvestaji(List<IzvestajDTO> stariIzvestaji) {
		this.stariIzvestaji = stariIzvestaji;
	}

	public Integer getZakazanaPoseta() {
		return zakazanaPoseta;
	}

	public void setZakazanaPoseta(Integer zakazanaPoseta) {
		this.zakazanaPoseta = zakazanaPoseta;
	}

}
