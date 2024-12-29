package com.example.demo.dto.pretraga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.model.KlinikaDTO;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Klinika;

public class KlinikaSlobodnoDTO extends KlinikaDTO {
	
	private List<PosetaDTO> posete;
	
	public KlinikaSlobodnoDTO() {
		super();
	}

	public KlinikaSlobodnoDTO(Klinika klinika) {
		super(klinika);
		this.posete = new ArrayList<>();
		for (Poseta p: klinika.getPosete()) {
			if (p.getTipPosete().isPregled() && p.getStanje().equals(StanjePosete.SLOBODNO) && 
					p.getDatum().after(new Date()))
				this.posete.add(new PosetaDTO(p));
		}
		Collections.sort(this.posete);
	}

	public List<PosetaDTO> getPosete() {
		return posete;
	}

	public void setPosete(List<PosetaDTO> posete) {
		this.posete = posete;
	}

}
