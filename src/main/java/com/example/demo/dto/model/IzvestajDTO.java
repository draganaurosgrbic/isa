package com.example.demo.dto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.model.posete.Izvestaj;
import com.example.demo.model.resursi.Dijagnoza;
import com.example.demo.model.resursi.Lek;

public class IzvestajDTO implements Comparable<IzvestajDTO>{
	
	private Integer id;
	private Date datum;
	private String opis;
	private List<Integer> dijagnoze;
	private List<Integer> lekovi;
	private List<String> dijagnozeSifre;
	private List<String> lekoviSifre;
	private Integer poseta;
	private Integer terapija;
	private String posetaNaziv;
	
	public IzvestajDTO() {
		super();
	}

	public IzvestajDTO(Izvestaj izvestaj) {
		super();
		this.id = izvestaj.getId();
		this.datum = izvestaj.getPoseta().getDatum();
		this.opis = izvestaj.getOpis();
		this.poseta = izvestaj.getPoseta().getId();
		this.terapija = izvestaj.getTerapija().getId();
		this.posetaNaziv = izvestaj.getPoseta().getTipPosete().getNaziv();
		
		this.dijagnozeSifre = new ArrayList<>();
		this.lekoviSifre = new ArrayList<>();
		
		this.dijagnoze = new ArrayList<>();
		for (Dijagnoza d : izvestaj.getDijagnoze()) {
			this.dijagnoze.add(d.getId());
			this.dijagnozeSifre.add(d.getSifra());
		}
		
		this.lekovi = new ArrayList<>();
		for (Lek l : izvestaj.getTerapija().getLekovi()) {
			this.lekovi.add(l.getId());
			this.lekoviSifre.add(l.getSifra());
		}
	}

	@Override
	public int compareTo(IzvestajDTO i) {
		return this.datum.compareTo(i.datum);
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Integer> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<Integer> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public List<Integer> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Integer> lekovi) {
		this.lekovi = lekovi;
	}

	public List<String> getDijagnozeSifre() {
		return dijagnozeSifre;
	}

	public void setDijagnozeSifre(List<String> dijagnozeSifre) {
		this.dijagnozeSifre = dijagnozeSifre;
	}

	public List<String> getLekoviSifre() {
		return lekoviSifre;
	}

	public void setLekoviSifre(List<String> lekoviSifre) {
		this.lekoviSifre = lekoviSifre;
	}

	public Integer getPoseta() {
		return poseta;
	}

	public void setPoseta(Integer poseta) {
		this.poseta = poseta;
	}

	public Integer getTerapija() {
		return terapija;
	}

	public void setTerapija(Integer terapija) {
		this.terapija = terapija;
	}

	public String getPosetaNaziv() {
		return posetaNaziv;
	}

	public void setPosetaNaziv(String posetaNaziv) {
		this.posetaNaziv = posetaNaziv;
	}

}
