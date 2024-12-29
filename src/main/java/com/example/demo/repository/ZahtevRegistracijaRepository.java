package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.zahtevi.ZahtevRegistracija;

public interface ZahtevRegistracijaRepository extends JpaRepository<ZahtevRegistracija, Integer>{

}
