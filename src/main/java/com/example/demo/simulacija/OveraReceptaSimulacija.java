package com.example.demo.simulacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.korisnici.Sestra;
import com.example.demo.service.SestraService;
import com.example.demo.service.TerapijaService;

@RestController
public class OveraReceptaSimulacija {

	@Autowired
	private TerapijaService terapijaService;

	@Autowired
	private SestraService sestraService;

	// u bazi je registrovana sestra koja ima id 24 i
	// terapija koja ima id 14, koja ima neoveren recept
	@Scheduled(cron = "10 46 14 * * *")
	public void overiRecept1() {
		System.out.println("Sestra 1 overava recept");
		Sestra s1 = this.sestraService.getOne(24);
		try {
			this.terapijaService.overi(14, s1);
			System.out.println("Sestra 1 uspesno overila recept!");
		} catch (ObjectOptimisticLockingFailureException e) {
			System.out.println("Sestra 1 pokusala da overi vec overen recept!");
		} catch (Exception e) {
			System.out.println("NEKA DRUGA GRESKA!");
		}
		System.out.println("Sestra 1 zavrsila sa radom!");
	}

	// u bazi je registrovana sestra koja ima id 23 i
	// terapija koja ima id 14, koja ima neoveren recept
	@Scheduled(cron = "10 46 14 * * *")
	public void overiRecept2() {
		System.out.println("Sestra 2 overava recept");
		Sestra s2 = this.sestraService.getOne(24);
		try {
			this.terapijaService.overi(14, s2);
			System.out.println("Sestra 2 uspesno overila recept!");
		} catch (ObjectOptimisticLockingFailureException e) {
			System.out.println("Sestra 2 pokusala da overi vec overen recept!");
		} catch (Exception e) {
			System.out.println("NEKA DRUGA GRESKA!");
		}
		System.out.println("Sestra 2 zavrsila sa radom!");
	}

}
