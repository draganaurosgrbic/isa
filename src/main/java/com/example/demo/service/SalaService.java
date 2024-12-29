package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.resursi.Sala;
import com.example.demo.repository.SalaRepository;

@Component
@Transactional(readOnly = true)
public class SalaService {

	@Autowired 
	private SalaRepository salaRepository;
	
	@Transactional(readOnly = false)
	public void save(Sala sala) {
		Sala temp = sala;
		if (sala.getId() != null) {
			sala = this.salaRepository.getOne(sala.getId());
			if (!sala.mozeBrisanje())
				throw new MyRuntimeException();
		}
		sala.setNaziv(temp.getNaziv());
		this.salaRepository.save(sala);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		
		Sala s = this.salaRepository.getOne(id);
		if (!s.mozeBrisanje())
			throw new MyRuntimeException();
		s.setAktivan(false);
		this.salaRepository.save(s);
		
	}
	
	@Transactional(readOnly = true)
	public List<Sala> findAll(Admin admin) {
		List<Sala> sale = new ArrayList<>();
		for (Sala s : this.salaRepository.findByKlinikaId(admin.getKlinika().getId())) {
			if (s.isAktivan())
				sale.add(s);
		}
		return sale;
	}
	
	@Transactional(readOnly = false)
	public Sala getOne(Integer id) {
		return this.salaRepository.getOne(id);
	}
	
}
