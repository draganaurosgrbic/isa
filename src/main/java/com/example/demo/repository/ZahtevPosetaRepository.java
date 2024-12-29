package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.zahtevi.ZahtevPoseta;

public interface ZahtevPosetaRepository extends JpaRepository<ZahtevPoseta, Integer>{
	
	List<ZahtevPoseta> findByKlinikaId(Integer id);

}
