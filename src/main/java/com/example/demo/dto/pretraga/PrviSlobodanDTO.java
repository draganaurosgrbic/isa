package com.example.demo.dto.pretraga;

import java.util.List;

import com.example.demo.dto.unos.ZahtevPregledObradaDTO;

public class PrviSlobodanDTO {

	private ZahtevPregledObradaDTO zahtev;
	private List<Integer> lekari;
	private Integer salaId;

	public PrviSlobodanDTO() {
		super();
	}

	public ZahtevPregledObradaDTO getZahtev() {
		return zahtev;
	}

	public void setZahtev(ZahtevPregledObradaDTO zahtev) {
		this.zahtev = zahtev;
	}

	public List<Integer> getLekari() {
		return lekari;
	}

	public void setLekari(List<Integer> lekari) {
		this.lekari = lekari;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

}
