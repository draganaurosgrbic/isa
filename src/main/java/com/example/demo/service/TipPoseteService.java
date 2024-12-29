package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.resursi.TipPosete;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
@Transactional(readOnly = true)
public class TipPoseteService {

	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
		
	@Transactional(readOnly = false)
	public void save(TipPosete tipPosete) {
		TipPosete temp = tipPosete;
		if (tipPosete.getId() != null) {
			tipPosete = this.tipPoseteRepository.getOne(tipPosete.getId());
			if (!tipPosete.mozeBrisanje())
				throw new MyRuntimeException();
		}
		tipPosete.setSati(temp.getSati());
		tipPosete.setMinute(temp.getMinute());
		tipPosete.setCena(temp.getCena());
		this.tipPoseteRepository.save(tipPosete);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		TipPosete tp = this.tipPoseteRepository.getOne(id);
		if (!tp.mozeBrisanje())
			throw new MyRuntimeException();
		for (Lekar l: this.lekarRepository.findBySpecijalizacijaId(id)) {
			if (l.isAktivan())
				throw new MyRuntimeException();
		}
		tp.setAktivan(false);
		this.tipPoseteRepository.save(tp);
	}
	
	@Transactional(readOnly = true)
	public List<TipPosete> findAll(Zaposleni zaposleni) {
		List<TipPosete> tipovi = new ArrayList<>();
		for (TipPosete tp : this.tipPoseteRepository.findByKlinikaId(zaposleni.getKlinika().getId())) {
			if (tp.isAktivan())
				tipovi.add(tp);
		}
		return tipovi;
	}
	
	@Transactional(readOnly = true)
	public Set<String> nazivi(){
		
		Set<String> lista = new HashSet<>();
		for (TipPosete tp: this.tipPoseteRepository.findAll()) {
			if (tp.isPregled())
				lista.add(tp.getNaziv());
		}
		return lista;
		
	}
	@Transactional(readOnly = true)
	public TipPosete getOne(Integer id) {
		return this.tipPoseteRepository.getOne(id);
	}
}
