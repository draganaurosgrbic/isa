package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.pretraga.KlinikaPretragaDTO;
import com.example.demo.dto.pretraga.LekarSatnicaDTO;
import com.example.demo.dto.unos.OcenaParamDTO;
import com.example.demo.dto.unos.PretragaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.ostalo.Ocena;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.resursi.Klinika;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.OcenaRepository;
import com.example.demo.repository.PosetaRepository;

@Component
@Transactional(readOnly = true)
public class KlinikaService {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private OcenaRepository ocenaRepository;
	
	@Autowired
	private PosetaRepository posetaRepository;
	
	@Autowired
	private LekarRepository lekarRepository;

	@Transactional(readOnly = false)
	public void save(Klinika klinika) {
		if (klinika.getId() != null)
			klinika.setOcene(this.klinikaRepository.getOne(klinika.getId()).getOcene());
		this.klinikaRepository.save(klinika);
	}
	
	@Transactional(readOnly = true)
	public List<Klinika> findAll(){
		return this.klinikaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Klinika getOne(Integer id) {
		return this.klinikaRepository.getOne(id);
	}
	
	@Transactional(readOnly = false)
	public Poseta ocenjivanje(Pacijent pacijent, OcenaParamDTO param, Integer posetaId) {
		
		Klinika k = this.klinikaRepository.getOne(param.getId());
		Ocena o = k.refreshOcena(pacijent, param.getOcena());
		this.ocenaRepository.save(o);
		return this.posetaRepository.getOne(posetaId);
		
	}

	@Transactional(readOnly = true)
	public List<KlinikaPretragaDTO> pretraga(PretragaDTO param) {

		Map<Integer, KlinikaPretragaDTO> mapa = new HashMap<>();
		for (Lekar l: this.lekarRepository.findAll()) {
			if (l.getSpecijalizacija().getNaziv().equalsIgnoreCase(param.getTipPregleda()) 
					&& l.getSpecijalizacija().isPregled()) {
				List<Date> satnica = l.getSatnica(param.getDatumPregleda());
				if (!satnica.isEmpty()) {
					LekarSatnicaDTO ls = new LekarSatnicaDTO(l, satnica);
					KlinikaPretragaDTO kp;
					if (mapa.containsKey(l.getKlinika().getId()))
						kp = mapa.get(l.getKlinika().getId());
					else {
						kp = new KlinikaPretragaDTO(l.getKlinika(), l.getSpecijalizacija().getCena(), l.getSpecijalizacija().getSati() + l.getSpecijalizacija().getMinute() / 60.0);
						mapa.put(l.getKlinika().getId(), kp);
					}
					kp.dodaj(ls);
				}
			}
			
		}

		List<KlinikaPretragaDTO> lista = new ArrayList<>();
		for (KlinikaPretragaDTO k: mapa.values()) {
			Collections.sort(k.getLekari());
			lista.add(k);
		}
		Collections.sort(lista);
		return lista;
		
	}
	
}
