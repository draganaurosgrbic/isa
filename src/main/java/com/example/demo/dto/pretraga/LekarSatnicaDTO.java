package com.example.demo.dto.pretraga;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.model.LekarDTO;
import com.example.demo.model.korisnici.Lekar;

public class LekarSatnicaDTO extends LekarDTO {
	
	private List<Date> satnica;

	public LekarSatnicaDTO() {
		super();
	}

	public LekarSatnicaDTO(Lekar lekar) {
		super(lekar);
		this.satnica = new ArrayList<>();
	}

	public LekarSatnicaDTO(Lekar l, List<Date> satnica) {
		super(l);
		this.satnica = satnica;
	}

	public List<Date> getSatnica() {
		return satnica;
	}

	public void setSatnica(List<Date> satnica) {
		this.satnica = satnica;
	}

}
