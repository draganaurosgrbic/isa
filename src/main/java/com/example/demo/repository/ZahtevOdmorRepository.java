package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.zahtevi.ZahtevOdmor;

public interface ZahtevOdmorRepository extends JpaRepository<ZahtevOdmor, Integer>{
	
	List<ZahtevOdmor> findByKlinikaId(Integer id);

}
