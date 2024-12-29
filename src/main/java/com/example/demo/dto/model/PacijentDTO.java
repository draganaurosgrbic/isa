package com.example.demo.dto.model;

import com.example.demo.model.korisnici.Pacijent;

public class PacijentDTO extends KorisnikDTO{

	private Integer karton;

	public PacijentDTO() {
		super();
	}

	public PacijentDTO(Pacijent pacijent) {
		super(pacijent);
		this.karton = pacijent.getKarton().getId();
	}

	public Integer getKarton() {
		return karton;
	}

	public void setKarton(Integer karton) {
		this.karton = karton;
	}
	
}
