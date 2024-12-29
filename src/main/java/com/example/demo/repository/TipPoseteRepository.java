package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.resursi.TipPosete;

public interface TipPoseteRepository extends JpaRepository<TipPosete, Integer>{
	
	List<TipPosete> findByKlinikaId(Integer id);

}
