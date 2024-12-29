package com.example.demo.dto.pretraga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.model.DijagnozaDTO;
import com.example.demo.dto.model.LekarDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.resursi.Dijagnoza;
import com.example.demo.model.resursi.Lek;

public class BolestDTO implements Comparable<BolestDTO>{
		
	private Integer posetaId;
	private Integer klinikaId;
	private Date datum;
	private String klinika;
	private String tipPosete;
	private String nazivPosete;
	private String izvestaj;
	private double ocenaKlinike;
	private List<LekarDTO> lekari;
	private List<DijagnozaDTO> dijagnoze;
	private List<ReceptDTO> recepti;
	
	public BolestDTO() {
		super();
	}

	public BolestDTO(Poseta poseta) {
		super();
		this.posetaId = poseta.getId();
		this.klinikaId = poseta.getSala().getKlinika().getId();
		this.datum = poseta.getDatum();
		this.klinika = poseta.getSala().getKlinika().getNaziv();
		this.tipPosete = poseta.getTipPosete().isPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = poseta.getTipPosete().getNaziv();
		this.izvestaj = poseta.getIzvestaj().getOpis();
		this.ocenaKlinike = poseta.getSala().getKlinika().prosecnaOcena();
		this.lekari = new ArrayList<>();
		for (Lekar l: poseta.getLekari())
			this.lekari.add(new LekarDTO(l));
		this.dijagnoze = new ArrayList<>();
		for (Dijagnoza d: poseta.getIzvestaj().getDijagnoze())
			this.dijagnoze.add(new DijagnozaDTO(d));
		this.recepti = new ArrayList<>();
		for (Lek l: poseta.getIzvestaj().getTerapija().getLekovi())
			this.recepti.add(new ReceptDTO(l, poseta.getIzvestaj().getTerapija().getSestra()));
		Collections.sort(this.lekari);
		Collections.sort(this.dijagnoze);
		Collections.sort(this.recepti);
	}

	@Override
	public int compareTo(BolestDTO b) {
		return this.datum.compareTo(b.datum);
	}

	public Integer getPosetaId() {
		return posetaId;
	}

	public void setPosetaId(Integer posetaId) {
		this.posetaId = posetaId;
	}

	public Integer getKlinikaId() {
		return klinikaId;
	}

	public void setKlinikaId(Integer klinikaId) {
		this.klinikaId = klinikaId;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getKlinika() {
		return klinika;
	}

	public void setKlinika(String klinika) {
		this.klinika = klinika;
	}

	public String getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(String tipPosete) {
		this.tipPosete = tipPosete;
	}

	public String getNazivPosete() {
		return nazivPosete;
	}

	public void setNazivPosete(String nazivPosete) {
		this.nazivPosete = nazivPosete;
	}

	public String getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(String izvestaj) {
		this.izvestaj = izvestaj;
	}

	public double getOcenaKlinike() {
		return ocenaKlinike;
	}

	public void setOcenaKlinike(double ocenaKlinike) {
		this.ocenaKlinike = ocenaKlinike;
	}

	public List<LekarDTO> getLekari() {
		return lekari;
	}

	public void setLekari(List<LekarDTO> lekari) {
		this.lekari = lekari;
	}

	public List<DijagnozaDTO> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<DijagnozaDTO> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public List<ReceptDTO> getRecepti() {
		return recepti;
	}

	public void setRecepti(List<ReceptDTO> recepti) {
		this.recepti = recepti;
	}
	
}
