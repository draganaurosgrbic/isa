package com.example.demo.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("super")
public class SuperAdmin extends Korisnik{

	public SuperAdmin() {
		super();
	}

	public SuperAdmin(Integer id, String email, String lozinka, String ime, String prezime, 
			String telefon, String drzava, String grad, String adresa, 
			boolean aktivan, boolean promenjenaSifra, long version) {
		super(id, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, 
				aktivan, promenjenaSifra, version);
	}

}
