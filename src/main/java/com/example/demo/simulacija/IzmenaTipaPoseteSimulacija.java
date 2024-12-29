package com.example.demo.simulacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.resursi.TipPosete;
import com.example.demo.service.TipPoseteService;


@RestController
public class IzmenaTipaPoseteSimulacija {

	//brisanje (izmena) tipa posete 1 
	
	@Autowired 
	private TipPoseteService tipService;
	
	@Scheduled(cron = "0 51 02 * * *")
	public void izmeniTipPoseteAdmin1() {
		
		System.out.println("ADMIN 1 KRENUO DA BRISE TIP POSETE");
		TipPosete tip = this.tipService.getOne(4);
		try {
			this.tipService.delete(tip.getId());
			System.out.println("ADMIN 1 IZBRISAO TIP POSETE 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 1 ZAKASNIO DA IZBRISE TIP POSETE 1!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 1 ZAVRSIO SA RADOM");
	}
	
	@Scheduled(cron = "0 51 02 * * *")
	public void izmeniTipPoseteAdmin2() {
		
		System.out.println("ADMIN 2 KRENUO DA BRISE TIP POSETE");
		TipPosete tip = this.tipService.getOne(4);
		try {
			this.tipService.delete(tip.getId());
			System.out.println("ADMIN 2 IZBRISAO TIP POSETE 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 2 ZAKASNIO DA IZBRISE TIP POSETE 1!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 2 ZAVRSIO SA RADOM");
	}
}