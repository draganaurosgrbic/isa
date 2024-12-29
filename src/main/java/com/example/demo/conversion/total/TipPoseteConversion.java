package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.TipPoseteDTO;
import com.example.demo.model.resursi.TipPosete;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class TipPoseteConversion {
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private TipPoseteRepository tipRepository;
	
	@Transactional(readOnly = true)
	public TipPosete get(TipPoseteDTO tipPoseteDTO) {
		long version;
		if (tipPoseteDTO.getId()!=null) {
			version = this.tipRepository.getOne(tipPoseteDTO.getId()).getVersion();
		}
		else {
			version = 0l;
		}
		return new TipPosete(tipPoseteDTO.getId(), 
				tipPoseteDTO.isPregled(), 
				tipPoseteDTO.getNaziv(), 
				tipPoseteDTO.getCena(), 
				tipPoseteDTO.getSati(), 
				tipPoseteDTO.getMinute(), 
				this.klinikaRepository.getOne(tipPoseteDTO.getKlinika()), 
				tipPoseteDTO.isAktivan(), version);
	}
	
	public TipPoseteDTO get(TipPosete tipPosete) {
		return new TipPoseteDTO(tipPosete);
	}
	
	public List<TipPoseteDTO> get(List<TipPosete> tipoviPoseta) {
		List<TipPoseteDTO> tipoviPosetaDTO = new ArrayList<>();
		for (TipPosete tp : tipoviPoseta)
			tipoviPosetaDTO.add(new TipPoseteDTO(tp));
		Collections.sort(tipoviPosetaDTO);
		return tipoviPosetaDTO;
	}

}
