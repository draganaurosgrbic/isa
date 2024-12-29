package com.example.demo.conversion.partial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.KartonDTO;
import com.example.demo.model.posete.Karton;
import com.example.demo.repository.PacijentRepository;

@Component
public class KartonConversion {
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Transactional(readOnly = true)
	public Karton get(KartonDTO kartonDTO) {
		
		return new Karton(kartonDTO.getId(), 
				kartonDTO.getBrojOsiguranika(), 
				kartonDTO.getVisina(), 
				kartonDTO.getTezina(), 
				kartonDTO.getLevaDioptrija(), 
				kartonDTO.getDesnaDioptrija(), 
				kartonDTO.getKrvnaGrupa(), 
				this.pacijentRepository.getOne(kartonDTO.getPacijent()));
	}
		
	public KartonDTO get(Karton karton) {
		return new KartonDTO(karton);
	}

}