package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.model.DijagnozaDTO;
import com.example.demo.model.resursi.Dijagnoza;

@Component
public class DijagnozaConversion {
	
	public Dijagnoza get(DijagnozaDTO dijagnozaDTO) {
		return new Dijagnoza(dijagnozaDTO.getId(), 
				dijagnozaDTO.getSifra(), 
				dijagnozaDTO.getNaziv());
	}
	
	public DijagnozaDTO get(Dijagnoza dijagnoza) {
		return new DijagnozaDTO(dijagnoza);
	}
	
	public List<DijagnozaDTO> get(List<Dijagnoza> dijagnoze){
		List<DijagnozaDTO> dijagnozeDTO = new ArrayList<>();
		for (Dijagnoza d: dijagnoze)
			dijagnozeDTO.add(new DijagnozaDTO(d));
		Collections.sort(dijagnozeDTO);
		return dijagnozeDTO;
	}
}
