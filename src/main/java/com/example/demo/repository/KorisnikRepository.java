package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.korisnici.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{

}
