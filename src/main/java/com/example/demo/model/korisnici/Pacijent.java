package com.example.demo.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.demo.model.posete.Karton;
import com.example.demo.model.posete.Poseta;

@Entity
@DiscriminatorValue("pacijent")
public class Pacijent extends Korisnik{
	
	@OneToOne
	@JoinColumn(name="karton")
	private Karton karton;

	public Pacijent() {
		super();
	}

	public Pacijent(Integer id, String email, String lozinka, String ime, String prezime, 
			String telefon, String drzava, String grad, String adresa, 
			boolean aktivan, boolean promenjenaSifra, Karton karton, long version) {
		super(id, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, 
				aktivan, promenjenaSifra, version);
		this.karton = karton;
	}

	public boolean posetioLekara(Lekar lekar) {
		
		for (Poseta p: this.karton.getPosete()) {
			for (Lekar l: p.getLekari()) {
				if (l.getId().equals(lekar.getId()))
					return true;
			}
		}
		return false;
		
	}
	
	public Karton getKarton() {
		return karton;
	}

	public void setKarton(Karton karton) {
		this.karton = karton;
	}

}
