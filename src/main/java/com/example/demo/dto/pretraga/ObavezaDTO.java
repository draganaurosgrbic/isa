package com.example.demo.dto.pretraga;

import java.text.SimpleDateFormat;

import com.example.demo.model.posete.Poseta;

public class ObavezaDTO {

	private Integer id;
	private String datum;
	private String pocetak;
	private Integer trajanje;
	private boolean pregled;
	private String tip;
	private String pacijent;

	public ObavezaDTO() {
		super();
	}

	public ObavezaDTO(Poseta poseta) {
		SimpleDateFormat formatDatum = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatPocetak = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.id = poseta.getId();
		this.datum = formatDatum.format(poseta.getDatum());
		this.pocetak = formatPocetak.format(poseta.getDatum());
		this.trajanje = poseta.sati() * 60 + poseta.minute();
		this.pregled = poseta.getTipPosete().isPregled();
		this.tip = poseta.getTipPosete().getNaziv();
		this.pacijent = poseta.getKarton() != null ? poseta.getKarton().getPacijent().getIme() + " " + poseta.getKarton().getPacijent().getPrezime() : "SLOBODNO";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}

}
