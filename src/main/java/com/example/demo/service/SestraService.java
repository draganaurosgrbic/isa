package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.korisnici.Sestra;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.repository.PacijentRepository;
import com.example.demo.repository.SestraRepository;

@Component
@Transactional(readOnly = true)
public class SestraService {

	@Autowired
	private SestraRepository sestraRepository;

	@Autowired
	private PacijentRepository pacijentRepository;

	@Transactional(readOnly = false)
	public void save(Sestra sestra) {
		this.sestraRepository.save(sestra);
	}

	@Transactional(readOnly = false)
	public void delete(Integer id) {
		Sestra s = this.sestraRepository.getOne(id);
		s.setAktivan(false);
		this.sestraRepository.save(s);
	}

	@Transactional(readOnly = true)
	public Sestra getOne(Integer id) {
		return this.sestraRepository.getOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<Sestra> findAll(Admin admin) {
		List<Sestra> sestre = new ArrayList<>();
		for (Sestra s : this.sestraRepository.findByKlinikaId(admin.getKlinika().getId())) {
			if (s.isAktivan())
				sestre.add(s);
		}
		return sestre;
	}

	@Transactional(readOnly = true)
	public List<Pacijent> pacijentiKlinike(Sestra sestra) {
		List<Pacijent> pacijenti = new ArrayList<>();
		for (Pacijent p : this.pacijentRepository.findAll()) {
			for (Poseta poseta : p.getKarton().getPosete()) {
				if (poseta.getSala().getKlinika().equals(sestra.getKlinika())
						&& poseta.getStanje().equals(StanjePosete.OBAVLJENO)) {
					pacijenti.add(p);
					break;
				}
			}
		}
		return pacijenti;
	}

}
