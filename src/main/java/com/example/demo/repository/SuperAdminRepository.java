package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.korisnici.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer>{

}
