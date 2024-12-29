package com.example.demo.simulacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.zahtevi.ZahtevRegistracija;
import com.example.demo.service.ZahtevRegistracijaService;

@RestController
public class ZahtevRegistracijaObradaSimulacija {

	@Autowired
	private ZahtevRegistracijaService zahtevRegistracijaService;

	// u bazi je registrovana sestra koja ima id 24 i
	// terapija koja ima id 14, koja ima neoveren recept
	@Scheduled(cron = "0 57 02 * * *")
	public void obradiZahtev1() {
		System.out.println("Super admin 1 pokusava da potvrdi zahtev");
		try {
			ZahtevRegistracija zahtev = this.zahtevRegistracijaService.getOne(1);
			this.zahtevRegistracijaService.potvrdi(zahtev);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Super admin 1 pokusao da potvrdi vec obradjen zahtev!");
		} catch (Exception e) {
			System.out.println("NEKA DRUGA GRESKA!");
		}
		System.out.println("Super admin 1 zavrsio sa radom!");
	}

	// u bazi je registrovana sestra koja ima id 23 i
	// terapija koja ima id 14, koja ima neoveren recept
	@Scheduled(cron = "0 57 02 * * *")
	public void obradiZahtev2() {
		System.out.println("Super admin 2 pokusava da potvrdi zahtev");
		try {
		ZahtevRegistracija zahtev = this.zahtevRegistracijaService.getOne(1);
		this.zahtevRegistracijaService.potvrdi(zahtev);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Super admin 2 pokusao da potvrdi vec obradjen zahtev!");
		} catch (Exception e) {
			System.out.println("NEKA DRUGA GRESKA!");
		}
		System.out.println("Super admin 2 zavrsio sa radom!");
	}

}
