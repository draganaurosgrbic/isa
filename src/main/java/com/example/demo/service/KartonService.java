package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.posete.Karton;
import com.example.demo.repository.KartonRepository;

@Component
@Transactional(readOnly = true)
public class KartonService {

	@Autowired
	private KartonRepository kartonRepository;
	
	@Transactional(readOnly = false)
	public void save(Karton karton) {
		this.kartonRepository.save(karton);
	}
	
	@Transactional(readOnly = true)
	public Karton getOne(Integer id) {
		return this.kartonRepository.getOne(id);
	}
	
}
