package com.example.demo.dto.pretraga;

import com.example.demo.model.posete.Poseta;

public class TerminDTO extends PosetaDTO {
	
	private String klinika;
	private String adresa;
	private String tipPosete;
	private double novaCena;
	
	public TerminDTO() {
		super();
	}

	public TerminDTO(Poseta poseta) {
		super(poseta);
		this.klinika = poseta.getTipPosete().getKlinika().getNaziv();
		this.adresa = poseta.getTipPosete().getKlinika().getAdresa();
		this.tipPosete = poseta.getTipPosete().isPregled() ? "PREGLED" : "OPERACIJA";
		this.novaCena = this.getPopust() != null ? this.getCena()- this.getCena() * this.getPopust() : this.getCena();
	}

	public String getKlinika() {
		return klinika;
	}

	public void setKlinika(String klinika) {
		this.klinika = klinika;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(String tipPosete) {
		this.tipPosete = tipPosete;
	}

	public double getNovaCena() {
		return novaCena;
	}

	public void setNovaCena(double novaCena) {
		this.novaCena = novaCena;
	}
	
}

