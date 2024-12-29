package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.korisnici.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Integer>{

}
