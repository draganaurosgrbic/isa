package com.example.demo.conversion.partial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.IzvestajDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Izvestaj;
import com.example.demo.model.posete.Terapija;
import com.example.demo.repository.DijagnozaRepository;
import com.example.demo.repository.IzvestajRepository;
import com.example.demo.repository.LekRepository;
import com.example.demo.repository.PosetaRepository;

@Component
public class IzvestajConversion {

	@Autowired
	private IzvestajRepository izvestajRepository;

	@Autowired
	private PosetaRepository posetaRepository;
	
	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
	@Autowired
	private LekRepository lekRepository;
			
	@Transactional(readOnly = true)
	public Izvestaj get(IzvestajDTO izvestajDTO, Lekar lekar) {
		
		Izvestaj izvestaj;
		Terapija terapija;
		
		if (izvestajDTO.getId() == null) {
			izvestaj = new Izvestaj();
			terapija = new Terapija();
			terapija.setIzvestaj(izvestaj);
			izvestaj.setTerapija(terapija);
			izvestaj.setPoseta(this.posetaRepository.getOne(lekar.getZapocetaPoseta().getId()));
		}
		else {
			izvestaj = this.izvestajRepository.getOne(izvestajDTO.getId());
			terapija = izvestaj.getTerapija();
		}
		
		izvestaj.setId(izvestajDTO.getId());
		izvestaj.setOpis(izvestajDTO.getOpis());
		izvestaj.getDijagnoze().clear();
		for (Integer id : izvestajDTO.getDijagnoze())
			izvestaj.getDijagnoze().add(this.dijagnozaRepository.getOne(id));
				
		izvestaj.getTerapija().getLekovi().clear();
		for (Integer id: izvestajDTO.getLekovi())
			terapija.getLekovi().add(this.lekRepository.getOne(id));
			
		return izvestaj;
	}
	
}
