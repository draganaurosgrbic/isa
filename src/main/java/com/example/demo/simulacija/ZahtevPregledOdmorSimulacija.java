package com.example.demo.simulacija;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Karton;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.model.zahtevi.ZahtevPoseta;
import com.example.demo.service.KartonService;
import com.example.demo.service.LekarService;
import com.example.demo.service.ZahtevOdmorService;
import com.example.demo.service.ZahtevPosetaService;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZahtevPregledOdmorSimulacija {
	
	@Autowired
	private KartonService kartonService;
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private ZahtevPosetaService zahtevPosetaService;

	@Autowired
	private ZahtevOdmorService zahtevOdmorService;
	
	//u bazi je registrovan karton koji ima id 1
	//korisnik koji ima id 10 je lekar koji se nalazi u bazi
	
	@Scheduled(cron = "0 26 14 * * *")
	public void zakaziPregledKlijent() {
		
		System.out.println("PACIJENT KRENUO DA ZAKAZUJE PREGLED");
		Karton karton1 = this.kartonService.getOne(1);
		Lekar lekar1 = this.lekarService.getOne(10);
		Date datum = new GregorianCalendar(2021, 1, 1, 14, 30).getTime();
		ZahtevPoseta z = new ZahtevPoseta(null, datum, karton1, lekar1, lekar1.getSpecijalizacija(), lekar1.getKlinika());
		try {
			this.zahtevPosetaService.save(z);
			System.out.println("PACIJENT USPESNO ZAKAZAO PREGLED KOD LEKARA!");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("PACIJENT ZAKASNIO DA ZAKAZE PREGLED KOD LEKARA!");
		}
		catch(Exception e) {
			System.out.println("DESILA SE NEKA DRUGA GRESKA KOD PACIJENTA!");
		}
		System.out.println("PACIJENT ZAVRSIO SA RADOM");		
		
	}
	
	@Scheduled(cron = "0 26 14 * * *")
	public void zakaziOdmorKlijent() {
		
		System.out.println("LEKAR KRENUO DA SALJE ZAHTEV ZA ODMOR");
		Lekar lekar1 = this.lekarService.getOne(10);
		Date datum1 = new GregorianCalendar(2021, 1, 1).getTime();
		Date datum2 = new GregorianCalendar(2021, 1, 10).getTime();
		ZahtevOdmor z = new ZahtevOdmor(null, datum1, datum2, false, lekar1, lekar1.getKlinika());
		try {
			this.zahtevOdmorService.save(z);
			System.out.println("LEKAR USPESNO POSLAO ZAHTEV ZA ODMOR!");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("LEKAR ZAKASNIO DA POSALJE ZAHTEV ZA ODMOR!");
		}
		catch(Exception e) {
			System.out.println("DESILA SE NEKA DRUGA GRESKA KOD LEKARA!");
		}
		System.out.println("LEKAR ZAVRSIO SA RADOM");
		
	}
	
}
