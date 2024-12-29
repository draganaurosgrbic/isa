package com.example.demo.dto.pretraga;

import com.example.demo.dto.model.ZahtevOdmorDTO;
import com.example.demo.model.zahtevi.ZahtevOdmor;

public class GodisnjiDTO extends ZahtevOdmorDTO{

	private int trajanje;

	public GodisnjiDTO() {
		super();
	}

	public GodisnjiDTO(ZahtevOdmor zahtev) {
		super(zahtev);
		this.trajanje = zahtev.getTrajanje();
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

}
