package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.ZahtevOdmorDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.resursi.Klinika;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.ZahtevOdmorRepository;
import com.example.demo.repository.ZaposleniRepository;

@Component
@Transactional(readOnly = true)
public class ZahtevOdmorService {

	@Autowired
	private ZahtevOdmorRepository zahtevOdmorRepository;

	@Autowired
	private LekarRepository lekarRepository;
	
	@Autowired
	private ZaposleniRepository zaposleniRepository;
				
	@Transactional(readOnly = false)
	public void save(ZahtevOdmor zahtev) {
		Zaposleni z = this.zaposleniRepository.getOne(zahtev.getZaposleni().getId());
		if (zahtev.getId() == null && 
				zahtev.getZaposleni().odmorPreklapanje(zahtev))
			throw new MyRuntimeException();
		this.zahtevOdmorRepository.save(zahtev);
		z = (Zaposleni) Hibernate.unproxy(z);
		if (z instanceof Lekar) {
			Lekar l = (Lekar) z;
			l.setPoslednjaIzmena(new Date());
			this.lekarRepository.save(l);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		this.zahtevOdmorRepository.deleteById(id);
	}
	
	public List<ZahtevOdmorDTO> findAll(Klinika klinika) {
		List<ZahtevOdmorDTO> zahtevi = new ArrayList<>();
		for (ZahtevOdmor z : this.zahtevOdmorRepository.findByKlinikaId(klinika.getId())) {
			if (!z.isOdobren())
				zahtevi.add(new ZahtevOdmorDTO(z));
		}
		return zahtevi;
	}
	
	@Transactional(readOnly = true)
	public ZahtevOdmor getOne(Integer id) {
		return this.zahtevOdmorRepository.getOne(id);
	}
	
}
