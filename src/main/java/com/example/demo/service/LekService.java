package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.resursi.Lek;
import com.example.demo.repository.LekRepository;

@Component
@Transactional(readOnly = true)
public class LekService {

	@Autowired
	private LekRepository lekRepository;
	
	@Transactional(readOnly = false)
	public void save(Lek lek) {
		this.lekRepository.save(lek);
	}

	@Transactional(readOnly = false)
	public void delete(Integer id) {
		this.lekRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Lek> findAll() {
		return this.lekRepository.findAll();
	}

}
