package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.ZahtevOdmorDTO;
import com.example.demo.dto.pretraga.GodisnjiDTO;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.ZaposleniRepository;

@Component
public class ZahtevOdmorConversion {
	
	@Autowired 
	private ZaposleniRepository zaposleniRepository;
	
	@Autowired 
	private KlinikaRepository klinikaRepository;
	
	@Transactional(readOnly = true)
	public ZahtevOdmor get(ZahtevOdmorDTO zahtevDTO) {
		return new ZahtevOdmor(zahtevDTO.getId(), 
				zahtevDTO.getPocetak(), 
				zahtevDTO.getKraj(),
				zahtevDTO.isOdobren(),
				this.zaposleniRepository.getOne(zahtevDTO.getZaposleni()), 
				this.klinikaRepository.getOne(zahtevDTO.getKlinika()));
	}
	
	
	public ZahtevOdmorDTO get(ZahtevOdmor zahtev) {
		return new ZahtevOdmorDTO(zahtev);
	}
	
	public List<ZahtevOdmorDTO> get(List<ZahtevOdmor> zahtevi){
		
		List<ZahtevOdmorDTO> zahteviDTO = new ArrayList<>();
		for (ZahtevOdmor z: zahtevi)
			zahteviDTO.add(new ZahtevOdmorDTO(z));
		Collections.sort(zahteviDTO);
		return zahteviDTO;
		
	}
	
	public List<GodisnjiDTO> get(Set<ZahtevOdmor> zahtevi){
		
		List<GodisnjiDTO> godisnjiDTO = new ArrayList<>();
		for (ZahtevOdmor z: zahtevi)
			godisnjiDTO.add(new GodisnjiDTO(z));
		Collections.sort(godisnjiDTO);
		return godisnjiDTO;
		
	}
}
