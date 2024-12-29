package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.posete.Izvestaj;
import com.example.demo.repository.IzvestajRepository;

@Component
@Transactional(readOnly = true)
public class IzvestajService {
	
	@Autowired
	private IzvestajRepository izvestajRepository;
	
	@Transactional(readOnly = false)
	public void save(Izvestaj izvestaj) {
		this.izvestajRepository.save(izvestaj);
	}

}
