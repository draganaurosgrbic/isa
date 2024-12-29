package com.example.demo.simulacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.resursi.Sala;
import com.example.demo.service.SalaService;
@RestController
public class IzmenaSaleSimulacija {

	//brisanje (izmena) sale 1
	
	@Autowired 
	private SalaService salaService;
	
	@Scheduled(cron = "20 46 14 * * *")
	public void izmeniSaluAdmin1() {
		
		System.out.println("ADMIN 1 KRENUO DA BRISE SALU");
		Sala sala1 = this.salaService.getOne(4);
		try {
			this.salaService.delete(sala1.getId());
			System.out.println("ADMIN 1 IZBRISAO SALU 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 1 ZAKASNIO DA IZBRISAO SALU 1!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 1 ZAVRSIO SA RADOM");
	}
	
	@Scheduled(cron = "20 46 14 * * *")
	public void izmeniSaluAdmin2() {
		
		System.out.println("ADMIN 2 KRENUO DA BRISE SALU");
		Sala sala1 = this.salaService.getOne(4);
		try {
			this.salaService.delete(sala1.getId());
			System.out.println("ADMIN 2 IZBRISAO SALU 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 2 ZAKASNIO DA IZBRISAO SALU 1!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 2 ZAVRSIO SA RADOM");
	}
}