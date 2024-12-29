package com.example.demo.dto.model;

import java.text.SimpleDateFormat;

import com.example.demo.model.korisnici.Zaposleni;

public class ZaposleniDTO extends KorisnikDTO{
	
	private String pocetnoVreme;
	private String krajnjeVreme;
	private Integer klinika;
	
	public ZaposleniDTO() {
		super();
	}
	
	public ZaposleniDTO(Zaposleni zaposleni) {
		super(zaposleni);
		this.klinika = zaposleni.getKlinika().getId();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		if (zaposleni.getPocetnoVreme() != null && zaposleni.getKrajnjeVreme() != null) {
			String pocetno = f.format(zaposleni.getPocetnoVreme());
			String krajnje = f.format(zaposleni.getKrajnjeVreme());
			this.pocetnoVreme = pocetno.substring(pocetno.length() - 5);
			this.krajnjeVreme = krajnje.substring(krajnje.length() - 5);
		}
	}

	public String getPocetnoVreme() {
		return pocetnoVreme;
	}

	public void setPocetnoVreme(String pocetnoVreme) {
		this.pocetnoVreme = pocetnoVreme;
	}

	public String getKrajnjeVreme() {
		return krajnjeVreme;
	}

	public void setKrajnjeVreme(String krajnjeVreme) {
		this.krajnjeVreme = krajnjeVreme;
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

}
