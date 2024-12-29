package com.example.demo.simulacija;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Karton;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Sala;
import com.example.demo.model.zahtevi.ZahtevPoseta;
import com.example.demo.service.KartonService;
import com.example.demo.service.LekarService;
import com.example.demo.service.PosetaService;
import com.example.demo.service.SalaService;
import com.example.demo.service.ZahtevPosetaService;

@RestController
public class ZahtevPregledPosetaSimulacija {

	@Autowired
	private KartonService kartonService;
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private ZahtevPosetaService zahtevPosetaService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private PosetaService posetaService;
	
	//u bazi je registrovan karton koji ima id 1
	//korisnik koji ima id 10 je lekar koji se nalazi u bazi
	//u bazi je registrovana sala koja ima id 1
	
	@Scheduled(cron = "00 46 14 * * *")
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
			System.out.println("NEKA DRUGA GRESKA SE DESILA KOD PACIJENTA!");
		}
		System.out.println("PACIJENT ZAVRSIO SA RADOM");		
		
	}

	@Scheduled(cron = "00 46 14 * * *")
	public void kreirajPosetuKlijent() {
		
		System.out.println("ADMIN KRENUO DA KREIRA NOVU POSETU");
		Date datum = new GregorianCalendar(2021, 1, 1, 14, 30).getTime();
		Sala sala = this.salaService.getOne(1);
		Lekar lekar1 = this.lekarService.getOne(10);
		Poseta p = new Poseta(StanjePosete.SLOBODNO, datum, 0.2, lekar1.getSpecijalizacija(), sala, null, lekar1);
		try {
			this.posetaService.save(p, null);
			System.out.println("ADMIN USPESNO KREIRAO NOVU POSETU!");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN ZAKASNIO DA KREIRA NOVU POSETU!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA SE DESILA KOD ADMINA!");
		}
		System.out.println("ADMIN ZAVRSIO SA RADOM");	
		
	}
	
}
