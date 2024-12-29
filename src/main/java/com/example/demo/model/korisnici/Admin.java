package com.example.demo.model.korisnici;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.demo.model.resursi.Klinika;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Zaposleni{

	public Admin() {
		super();
	}

	public Admin(Integer id, String email, String lozinka, String ime, String prezime, 
			String telefon, String drzava, String grad, String adresa, 
			boolean aktivan, boolean promenjenaSifra, Date pocetnoVreme, Date krajnjeVreme,
			Klinika klinika, long version) {
		super(id, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, 
				aktivan, promenjenaSifra, pocetnoVreme, krajnjeVreme, klinika, version);
	}
	
}
