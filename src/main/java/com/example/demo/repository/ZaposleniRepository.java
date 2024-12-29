package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.korisnici.Zaposleni;

public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer>{

}
