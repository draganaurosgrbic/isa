package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.model.ZahtevRegistracijaDTO;
import com.example.demo.model.zahtevi.ZahtevRegistracija;

@Component
public class ZahtevRegistracijaConversion {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ZahtevRegistracija get(ZahtevRegistracijaDTO zahtevDTO) {
				
		return new ZahtevRegistracija(zahtevDTO.getId(), 
				zahtevDTO.getEmail(), 
				this.passwordEncoder.encoder().encode(zahtevDTO.getLozinka()), 
				zahtevDTO.getIme(), 
				zahtevDTO.getPrezime(), 
				zahtevDTO.getTelefon(), 
				zahtevDTO.getBrojOsiguranika(), 
				zahtevDTO.getDrzava(), 
				zahtevDTO.getGrad(), 
				zahtevDTO.getAdresa());
		
	}
	
	public ZahtevRegistracijaDTO get(ZahtevRegistracija zahtev) {
		return new ZahtevRegistracijaDTO(zahtev);
	}
	
	public List<ZahtevRegistracijaDTO> get(List<ZahtevRegistracija> zahtevi){
		
		List<ZahtevRegistracijaDTO> zahteviDTO = new ArrayList<>();
		for (ZahtevRegistracija z: zahtevi)
			zahteviDTO.add(new ZahtevRegistracijaDTO(z));
		Collections.sort(zahteviDTO);
		return zahteviDTO;
		
	}

}
