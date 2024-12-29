package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.conversion.total.PasswordEncoder;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.repository.PacijentRepository;

@Component
@Transactional(readOnly = true)
public class PacijentService {

	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = false)
	public void save(Pacijent pacijent) {
		this.pacijentRepository.save(pacijent);
	}

	@Transactional(readOnly = false)
	public Pacijent getOne(Integer id) {
		return this.pacijentRepository.getOne(id);
	}
	
	@Transactional(readOnly = false)
	public boolean aktiviranje(String id) {

		for (Pacijent p: this.pacijentRepository.findAll()) {
			if (this.passwordEncoder.encoder().matches(p.getId() + "", id)) {
				if (p.isAktivan())
					return false;
				p.setAktivan(true);
				this.pacijentRepository.save(p);
				return true;
			}
		}
		
		throw new MyRuntimeException();
	}
	
}
