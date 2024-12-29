package com.example.demo.simulacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.posete.Karton;
import com.example.demo.service.KartonService;
import com.example.demo.service.PosetaService;

@RestController
public class PredefinisaniPregledSimulacija {

	@Autowired
	private PosetaService posetaService;
	
	@Autowired
	private KartonService kartonService;
	
	//u bazi je registrovan karton koji ima id 1
	//poseta koja ima id 15 je predefinisani pregled koji se nalazi u bazi
	
	@Scheduled(cron = "0 01 03 * * *")
	public void zakaziPredefinisaniPregledKlijent1() {
		
		System.out.println("PACIJENT 1 KRENUO DA ZAKAZUJE PREGLED");
		Karton karton1 = this.kartonService.getOne(1);
		try {
			this.posetaService.zakazi(15, karton1);	
			System.out.println("PACIJENT 1 USPESNO ZAKAZAO PREDEFINISANI PREGLED!");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("PACIJENT 1 ZAKASNIO DA ZAKAZE PREDEFISANI PREGLED!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE KOD PACIJENTA 1!");
		}
		System.out.println("PACIJENT 1 ZAVRSIO SA RADOM");
		
	}
	
	@Scheduled(cron = "0 01 03 * * *")
	public void zakaziPredefinisaniPregledKlijent2() {
		
		System.out.println("PACIJENT 2 KRENUO DA ZAKAZUJE PREGLED");
		Karton karton2 = this.kartonService.getOne(1);
		try {
			this.posetaService.zakazi(15, karton2);	
			System.out.println("PACIJENT 2 USPESNO ZAKAZAO PREDEFINISANI PREGLED!");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("PACIJENT 2 ZAKASNIO DA ZAKAZE PREDEFISANI PREGLED!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE KOD PACIJENTA 2!");
		}
		System.out.println("PACIJENT 2 ZAVRSIO SA RADOM");
	}

	
}
