package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.resursi.Dijagnoza;
import com.example.demo.repository.DijagnozaRepository;

@Component
@Transactional(readOnly = true)
public class DijagnozaService {

	@Autowired
	private DijagnozaRepository dijagnozaRepository;

	@Transactional(readOnly = false)
	public void save(Dijagnoza dijagnoza) {
		this.dijagnozaRepository.save(dijagnoza);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		this.dijagnozaRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Dijagnoza> findAll() {
		return this.dijagnozaRepository.findAll();
	}

}
