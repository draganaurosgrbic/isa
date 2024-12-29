package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Admin;
import com.example.demo.repository.AdminRepository;

@Component
@Transactional(readOnly = true)
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Transactional(readOnly = false)
	public void save(Admin admin) {
		this.adminRepository.save(admin);
	}
	@Transactional(readOnly = true)
	public Admin getOne(Integer id) {
		return this.adminRepository.getOne(id);
	}
}
