package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.korisnici.Sestra;

public interface SestraRepository extends JpaRepository<Sestra, Integer> {
	
	List<Sestra> findByKlinikaId(Integer id);

}
