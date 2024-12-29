package com.example.demo.simulacija;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conversion.partial.PosetaConversion;
import com.example.demo.dto.model.SalaDTO;
import com.example.demo.dto.unos.ZahtevPregledObradaDTO;
import com.example.demo.model.posete.Poseta;
import com.example.demo.service.PosetaService;
import com.example.demo.service.SalaService;
import com.example.demo.service.ZahtevPosetaService;

@RestController
public class ZakazivanjeSaleSimulacija {
	
	@Autowired
	private PosetaConversion posetaConversion;

	@Autowired
	private SalaService salaService;


	@Autowired
	private PosetaService posetaService;
	
	@Autowired
	private ZahtevPosetaService zahtevPosetaService;
	
	//dva admina pokusavaju konkuretno da za isti datum i vreme rezervisu salu id=1
	//za dva razlicita zahteva za pregled, zahtev id=1 i zahtev id=2 

	@Scheduled(cron = "0 59 02 * * *")
	public void rezervisiSaluAdmin1() throws ParseException {
		
		System.out.println("ADMIN 1 KRENUO DA REZERVISE SALU");
		ZahtevPregledObradaDTO zahtev1 = new ZahtevPregledObradaDTO(this.zahtevPosetaService.getOne(1));
		SalaDTO sala = new SalaDTO(this.salaService.getOne(1));
		Poseta poseta = this.posetaConversion.get(zahtev1, sala);
		
		try {
			this.posetaService.save(poseta, zahtev1.getId());
			System.out.println("ADMIN 1 REZERVISAO SALU 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 1 ZAKASNIO DA ZAKAZE SALU 1!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 1 ZAVRSIO SA RADOM");
	}
	
	@Scheduled(cron = "0 59 02 * * *")
	public void rezervisiSaluAdmin2() throws ParseException {
		
		System.out.println("ADMIN 2 KRENUO DA REZERVISE SALU");
		ZahtevPregledObradaDTO zahtev2 = new ZahtevPregledObradaDTO(this.zahtevPosetaService.getOne(2));
		SalaDTO sala = new SalaDTO(this.salaService.getOne(1));
		Poseta poseta = this.posetaConversion.get(zahtev2, sala);

		try {
			this.posetaService.save(poseta, zahtev2.getId());
			System.out.println("ADMIN 2 REZERVISAO SALU 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 2 ZAKASNIO DA ZAKAZE SALU 1!");
		}
		catch(Exception e) {
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 2 ZAVRSIO SA RADOM");
	}
}
