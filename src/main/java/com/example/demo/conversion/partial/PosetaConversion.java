package com.example.demo.conversion.partial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.SalaDTO;
import com.example.demo.dto.pretraga.PosetaDTO;
import com.example.demo.dto.unos.PredefinisanaPosetaDTO;
import com.example.demo.dto.unos.ZahtevOperacijaObradaDTO;
import com.example.demo.dto.unos.ZahtevPregledObradaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.PacijentRepository;
import com.example.demo.repository.SalaRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class PosetaConversion {

	@Autowired
	private TipPoseteRepository tipPoseteRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private PacijentRepository pacijentRepository;

	@Autowired
	private LekarRepository lekarRepository;
	
	private final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");

	@Transactional(readOnly = true)
	public Poseta get(PredefinisanaPosetaDTO posetaDTO) throws ParseException {

		String temp1 = this.f.format(posetaDTO.getDatum());
		String temp2 = temp1.substring(0, temp1.length() - 6);
		return new Poseta(StanjePosete.SLOBODNO, 
				this.f.parse(temp2 + " " + posetaDTO.getVreme()), 
				posetaDTO.getPopust(),
				this.tipPoseteRepository.getOne(posetaDTO.getTipPregleda()), 
				this.salaRepository.getOne(posetaDTO.getSala()),
				null, this.lekarRepository.getOne(posetaDTO.getLekar()));	
	}
	public PosetaDTO get(Poseta poseta) {
		return new PosetaDTO(poseta);
	}

	@Transactional(readOnly = true)
	public Poseta get(ZahtevPregledObradaDTO zahtevDTO, SalaDTO salaDTO) throws ParseException {
		Date pocetak = this.f.parse(zahtevDTO.getDatum());
		return new Poseta(StanjePosete.ZAUZETO, 
				pocetak, 
				null,
				this.tipPoseteRepository.getOne(zahtevDTO.getIdTipa()),
				this.salaRepository.getOne(salaDTO.getId()), 
				this.pacijentRepository.getOne(zahtevDTO.getIdPacijent()).getKarton(),
				this.lekarRepository.getOne(zahtevDTO.getIdLekar()));
	}

	@Transactional(readOnly = true)
	public Poseta get(ZahtevOperacijaObradaDTO zahtevDTO, SalaDTO salaDTO) throws ParseException {

		Date pocetak = this.f.parse(zahtevDTO.getPocetak());
		Set<Lekar> lekari = new HashSet<>();

		for (Lekar l : this.lekarRepository.findAll()) {
			if (zahtevDTO.getLekariId().contains(l.getId())) {
				lekari.add(l);
			}
		}

		return new Poseta(StanjePosete.ZAUZETO, 
				pocetak, 
				null,
				this.tipPoseteRepository.getOne(zahtevDTO.getTipId()),
				this.salaRepository.getOne(zahtevDTO.getSalaId()), 
				this.pacijentRepository.getOne(zahtevDTO.getPacijentId()).getKarton(),
				lekari);
	}

}
