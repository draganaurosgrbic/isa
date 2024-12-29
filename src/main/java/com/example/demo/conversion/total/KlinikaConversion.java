package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.KlinikaDTO;
import com.example.demo.dto.pretraga.KlinikaPretragaDTO;
import com.example.demo.dto.pretraga.KlinikaSlobodnoDTO;
import com.example.demo.model.resursi.Klinika;
import com.example.demo.repository.KlinikaRepository;

@Component
public class KlinikaConversion {
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public Klinika get(KlinikaDTO klinikaDTO) {
		return new Klinika(klinikaDTO.getId(), 
				klinikaDTO.getNaziv(), 
				klinikaDTO.getOpis(), 
				klinikaDTO.getAdresa());
	}
	
	public KlinikaDTO get(Klinika klinika) {
		return new KlinikaDTO(klinika);
	}
	
	public List<KlinikaDTO> get(List<Klinika> klinike){
		
		List<KlinikaDTO> klinikeDTO = new ArrayList<>();
		for (Klinika k: klinike) 
			klinikeDTO.add(new KlinikaDTO(k));
		Collections.sort(klinikeDTO);
		return klinikeDTO;
		
	}
	
	@Transactional(readOnly = true)
	public List<KlinikaPretragaDTO> getPretraga() {

		List<KlinikaPretragaDTO> lista = new ArrayList<>();
		for (Klinika k: this.klinikaRepository.findAll())
			lista.add(new KlinikaPretragaDTO(k));
		Collections.sort(lista);
		return lista;
		
	}
	
	@Transactional(readOnly = true)
	public List<KlinikaSlobodnoDTO> getSlobodno() {
		
		List<KlinikaSlobodnoDTO> lista = new ArrayList<>();
		for (Klinika k: this.klinikaRepository.findAll()) {
			KlinikaSlobodnoDTO temp = new KlinikaSlobodnoDTO(k);
			if (!temp.getPosete().isEmpty())
				lista.add(temp);
		}
		Collections.sort(lista);
		return lista;

	}
	
}