package com.example.demo.conversion.total;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.LekarDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Poseta;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class LekarConversion {

	@Autowired
	private LekarRepository lekarRepository;

	@Autowired
	private KlinikaRepository klinikaRepository;

	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");

	@Transactional(readOnly = true)
	public Lekar get(LekarDTO lekarDTO) throws ParseException {

		String baseDate = "2020-04-20 ";
		long version;
		String lozinka;
		Poseta zapoceto = null;
		
		if (lekarDTO.getId() != null) {
			Lekar temp = this.lekarRepository.getOne(lekarDTO.getId());
			version = temp.getVersion();		
			if (!lekarDTO.getLozinka().equals(temp.getLozinka()))
				lozinka = this.passwordEncoder.encoder().encode(lekarDTO.getLozinka());
			else
				lozinka = lekarDTO.getLozinka();
			zapoceto = temp.getZapocetaPoseta();
		}
		else {
			version = 0l;	
			lozinka = this.passwordEncoder.encoder().encode(lekarDTO.getLozinka());
		}

		Lekar result = new Lekar(lekarDTO.getId(), 
				lekarDTO.getEmail(), 
				lozinka, 
				lekarDTO.getIme(),
				lekarDTO.getPrezime(), 
				lekarDTO.getTelefon(), 
				lekarDTO.getDrzava(), 
				lekarDTO.getGrad(),
				lekarDTO.getAdresa(), 
				lekarDTO.isAktivan(), 
				lekarDTO.isPromenjenaSifra(),
				this.f.parse(baseDate + lekarDTO.getPocetnoVreme()), 
				this.f.parse(baseDate + lekarDTO.getKrajnjeVreme()),
				this.klinikaRepository.getOne(lekarDTO.getKlinika()),
				this.tipPoseteRepository.getOne(lekarDTO.getSpecijalizacija()), version);
		if (zapoceto != null)
			result.setZapocetaPoseta(zapoceto);
		return result;
		
	}

	public LekarDTO get(Lekar lekar) {
		return new LekarDTO(lekar);
	}

	public List<LekarDTO> get(List<Lekar> lekari) {

		List<LekarDTO> lekariDTO = new ArrayList<>();
		for (Lekar l : lekari)
			lekariDTO.add(new LekarDTO(l));
		Collections.sort(lekariDTO);
		return lekariDTO;

	}

}
