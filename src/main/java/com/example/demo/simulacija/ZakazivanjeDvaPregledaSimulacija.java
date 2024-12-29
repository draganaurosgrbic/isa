package com.example.demo.simulacija;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Karton;
import com.example.demo.model.zahtevi.ZahtevPoseta;
import com.example.demo.service.KartonService;
import com.example.demo.service.LekarService;
import com.example.demo.service.ZahtevPosetaService;

@RestController
public class ZakazivanjeDvaPregledaSimulacija {
	
	@Autowired
	private KartonService kartonService;
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private ZahtevPosetaService zahtevPosetaService;
	
	//u bazi je registrovan karton koji ima id 1
	//korisnik koji ima id 10 je lekar koji se nalazi u bazi

	@Scheduled(cron = "0 02 03 * * *")
	public void zakaziPregledKlijent1() {
		
		System.out.println("PACIJENT 1 KRENUO DA ZAKAZUJE PREGLED");
		Karton karton1 = this.kartonService.getOne(1);
		Lekar lekar1 = this.lekarService.getOne(10);
		Date datum = new GregorianCalendar(2021, 1, 1, 14, 30).getTime();
		ZahtevPoseta z = new ZahtevPoseta(null, datum, karton1, lekar1, lekar1.getSpecijalizacija(), lekar1.getKlinika());
		try {
			this.zahtevPosetaService.save(z);
			System.out.println("PACIJENT 1 USPESNO ZAKAZAO PREGLED KOD LEKARA!");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("PACIJENT 1 ZAKASNIO DA ZAKAZE PREGLED KOD LEKARA!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE KOD PACIJENTA 1!");
		}
		System.out.println("PACIJENT 1 ZAVRSIO SA RADOM");
	}
	
	@Scheduled(cron = "0 02 03 * * *")
	public void zakaziPregledKlijent2() {
		
		System.out.println("PACIJENT 2 KRENUO DA ZAKAZUJE PREGLED");
		Karton karton2 = this.kartonService.getOne(2);
		Lekar lekar1 = this.lekarService.getOne(10);
		Date datum = new GregorianCalendar(2021, 1, 1, 14, 30).getTime();
		ZahtevPoseta z = new ZahtevPoseta(null, datum, karton2, lekar1, lekar1.getSpecijalizacija(), lekar1.getKlinika());
		try {
			this.zahtevPosetaService.save(z);
			System.out.println("PACIJENT 2 USPESNO ZAKAZAO PREGLED KOD LEKARA!");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("PACIJENT 2 ZAKASNIO DA ZAKAZE PREGLED KOD LEKARA!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE KOD PACIJENTA 2!");
		}
		System.out.println("PACIJENT 2 ZAVRSIO SA RADOM");
	}
	
}
