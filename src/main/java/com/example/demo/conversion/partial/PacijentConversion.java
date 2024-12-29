package com.example.demo.conversion.partial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.conversion.total.PasswordEncoder;
import com.example.demo.dto.model.PacijentDTO;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.repository.KartonRepository;
import com.example.demo.repository.PacijentRepository;

@Component
public class PacijentConversion {
		
	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Autowired
	private KartonRepository kartonRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public Pacijent get(PacijentDTO pacijentDTO) {
		
		long version;
		String lozinka;
		
		if (pacijentDTO.getId() != null) {
			Pacijent temp = this.pacijentRepository.getOne(pacijentDTO.getId());
			version = temp.getVersion();
			if (!pacijentDTO.getLozinka().equals(temp.getLozinka()))
				lozinka = this.passwordEncoder.encoder().encode(pacijentDTO.getLozinka());
			else
				lozinka = pacijentDTO.getLozinka();
		}
		else {
			version = 0l;		
			lozinka = this.passwordEncoder.encoder().encode(pacijentDTO.getLozinka());
		}
				
		return new Pacijent(pacijentDTO.getId(), 
				pacijentDTO.getEmail(), 
				lozinka, 
				pacijentDTO.getIme(), 
				pacijentDTO.getPrezime(), 
				pacijentDTO.getTelefon(), 
				pacijentDTO.getDrzava(), 
				pacijentDTO.getGrad(), 
				pacijentDTO.getAdresa(), 
				pacijentDTO.isAktivan(), 
				pacijentDTO.isPromenjenaSifra(), 
				this.kartonRepository.getOne(pacijentDTO.getKarton()), 
				version);
		
	}
	
	public PacijentDTO get(Pacijent pacijent) {
		return new PacijentDTO(pacijent);
	}
	
}
