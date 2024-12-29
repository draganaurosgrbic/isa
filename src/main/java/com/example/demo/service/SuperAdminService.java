package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.SuperAdmin;
import com.example.demo.repository.SuperAdminRepository;

@Component
@Transactional(readOnly = true)
public class SuperAdminService {
	
	@Autowired
	private SuperAdminRepository superAdminRepository;
	
	@Transactional(readOnly = false)
	public void save(SuperAdmin superAdmin) {
		this.superAdminRepository.save(superAdmin);
	}
	
}
