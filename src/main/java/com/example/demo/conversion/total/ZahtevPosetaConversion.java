  
package com.example.demo.conversion.total;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.ZahtevPosetaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.zahtevi.ZahtevPoseta;
import com.example.demo.repository.KartonRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class ZahtevPosetaConversion {

	@Autowired
	private KartonRepository kartonRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
	
	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	private final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		
	@Transactional(readOnly = true)
	public ZahtevPoseta get(ZahtevPosetaDTO zahtevDTO) throws ParseException {
				
		String temp1 = this.f.format(zahtevDTO.getDatum());
		String temp2 = temp1.substring(0, temp1.length() - 6);
		Date datum = zahtevDTO.getVreme() == null ? zahtevDTO.getDatum() : this.f.parse(temp2 + " " + zahtevDTO.getVreme());
		Lekar temp = this.lekarRepository.getOne(zahtevDTO.getLekar());
		return new ZahtevPoseta(zahtevDTO.getId(), 
				datum, 
				this.kartonRepository.getOne(zahtevDTO.getKarton()), 
				this.lekarRepository.getOne(zahtevDTO.getLekar()), 
				zahtevDTO.getTipPosete() != null ? this.tipPoseteRepository.getOne(zahtevDTO.getTipPosete()) : 
					temp.getSpecijalizacija(), 
					temp.getKlinika());
	}
	
	
	public ZahtevPosetaDTO get(ZahtevPoseta zahtev) {
		return new ZahtevPosetaDTO(zahtev);
	}
	
	public List<ZahtevPosetaDTO> get(List<ZahtevPoseta> zahtevi){
		List<ZahtevPosetaDTO> zahteviDTO = new ArrayList<>();
		for (ZahtevPoseta z: zahtevi)
			zahteviDTO.add(new ZahtevPosetaDTO(z));
		Collections.sort(zahteviDTO);
		return zahteviDTO;
	}

}
