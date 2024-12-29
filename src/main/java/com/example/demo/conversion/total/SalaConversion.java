package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.SalaDTO;
import com.example.demo.model.resursi.Sala;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.SalaRepository;

@Component
public class SalaConversion {
	
	@Autowired 
	private KlinikaRepository klinikaRepository;
	
	@Autowired 
	private SalaRepository salaRepository;
	
	@Transactional(readOnly = true)
	public Sala get(SalaDTO salaDTO) {
		long version;
		if (salaDTO.getId()!=null) {
			version = this.salaRepository.getOne(salaDTO.getId()).getVersion();
		}
		else {
			version = 0l;
		}
		return new Sala(salaDTO.getId(), 
				salaDTO.getBroj(), 
				salaDTO.getNaziv(), 
				this.klinikaRepository.getOne(salaDTO.getKlinika()), salaDTO.isAktivan(), version);
	}
	
	public SalaDTO get(Sala sala) {
		return new SalaDTO(sala);
	}
	
	public List<SalaDTO> get(List<Sala> sale) {
		List<SalaDTO> saleDTO = new ArrayList<>();
		for (Sala s : sale) 
			saleDTO.add(new SalaDTO(s));
		Collections.sort(saleDTO);
		return saleDTO;
 	}

}
