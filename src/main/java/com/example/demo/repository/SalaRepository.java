package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.resursi.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer>{
	
	List<Sala> findByKlinikaId(Integer id);

}
