package com.example.demo.model.ostalo;

import com.example.demo.model.korisnici.Pacijent;

public interface Ocenjivanje {

	public Ocena refreshOcena(Pacijent pacijent, int ocena);
	public double prosecnaOcena();
	
}
