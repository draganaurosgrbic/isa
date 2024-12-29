package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.korisnici.Lekar;

public interface LekarRepository extends JpaRepository<Lekar, Integer>{
	
	List<Lekar> findByKlinikaId(Integer id);
	List<Lekar> findBySpecijalizacijaId(Integer id);

}
