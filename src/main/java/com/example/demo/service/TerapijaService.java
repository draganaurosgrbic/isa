package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Sestra;
import com.example.demo.model.posete.Terapija;
import com.example.demo.repository.TerapijaRepository;

@Component
@Transactional(readOnly = true)
public class TerapijaService {

	@Autowired
	private TerapijaRepository terapijaRepository;
	
	@Transactional(readOnly = false)
	public void save(Terapija terapija) {
		this.terapijaRepository.save(terapija);
	}

	@Transactional(readOnly = true)
	public List<Terapija> neovereno(Sestra sestra) {
		List<Terapija> terapije = new ArrayList<>();
		
		for (Terapija t : this.terapijaRepository.findAll()) {
			if (t.getSestra() == null && 
					t.getIzvestaj().getPoseta().getSala().getKlinika().
					getId().equals(sestra.getKlinika().getId())) 
				terapije.add(t);

		}
		
		return terapije;
	}

	@Transactional(readOnly = false)
	public void overi(Integer id, Sestra sestra) {
		
		Terapija t = this.terapijaRepository.getOne(id);
		
		if (t.getSestra() != null)
			throw new MyRuntimeException();
		
		t.setSestra(sestra);
		this.terapijaRepository.save(t);
				
	}

}
