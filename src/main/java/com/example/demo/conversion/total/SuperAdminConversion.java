package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.SuperAdminDTO;
import com.example.demo.model.korisnici.SuperAdmin;
import com.example.demo.repository.SuperAdminRepository;

@Component
public class SuperAdminConversion {
	
	@Autowired
	private SuperAdminRepository superAdminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public SuperAdmin get(SuperAdminDTO superAdminDTO) {
		
		long version;
		String lozinka;
		
		if (superAdminDTO.getId() != null) {
			SuperAdmin temp = this.superAdminRepository.getOne(superAdminDTO.getId());
			version = temp.getVersion();
			if (!superAdminDTO.getLozinka().equals(temp.getLozinka()))
				lozinka = this.passwordEncoder.encoder().encode(superAdminDTO.getLozinka());
			else
				lozinka = superAdminDTO.getLozinka();
		}
		else {
			version = 0l;
			lozinka = this.passwordEncoder.encoder().encode(superAdminDTO.getLozinka());
		}
				
		return new SuperAdmin(superAdminDTO.getId(), 
				superAdminDTO.getEmail(), 
				lozinka, 
				superAdminDTO.getIme(), 
				superAdminDTO.getPrezime(), 
				superAdminDTO.getTelefon(), 
				superAdminDTO.getDrzava(), 
				superAdminDTO.getGrad(), 
				superAdminDTO.getAdresa(), 
				superAdminDTO.isAktivan(), 
				superAdminDTO.isPromenjenaSifra(), 
				version);
		
	}
	
	public SuperAdminDTO get(SuperAdmin superAdmin) {
		return new SuperAdminDTO(superAdmin);
	}
	
	public List<SuperAdminDTO> get(List<SuperAdmin> superAdmini){
		
		List<SuperAdminDTO> superAdminiDTO = new ArrayList<>();
		for (SuperAdmin sa: superAdmini)
			superAdminiDTO.add(new SuperAdminDTO(sa));
		Collections.sort(superAdminiDTO);
		return superAdminiDTO;
		
	}
	
}
