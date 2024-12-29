package com.example.demo.simulacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.unos.OcenaParamDTO;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.service.LekarService;
import com.example.demo.service.PacijentService;

@RestController
public class PrvaOcenaSimulacija {
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private LekarService lekarService;
	
	//korisnik koji ima id 1 je pacijent koji se nalazi u bazi
	//korisnik koji ima id 10 je lekar koji se nalazi u bazi

	@Scheduled(cron = "0 54 02 * * *")
	public void kreirajPrvuOcenuKlijent1() {
		
		System.out.println("KLIJENT 1 KRENUO DA KREIRA PRVU OCENU ZA LEKARA");
		Pacijent pacijent1 = this.pacijentService.getOne(1);
		OcenaParamDTO ocena = new OcenaParamDTO();
		ocena.setId(10);
		ocena.setOcena(10);
		try {
			this.lekarService.ocenjivanje(pacijent1, ocena);
			System.out.println("KLIJENT 1 USPESNO PRVI PUT OCENIO LEKARA!");
		}
		catch(Exception e) {
			System.out.println("KLIJENT 1 ZAKASNIO DA PRVI PUT OCENI LEKARA!");
		}
		System.out.println("KLIJENT 1 ZAVSIO SA RADOM");
		
	}
	
	@Scheduled(cron = "0 54 02 * * *")
	public void kreirajPrvuOcenuKlijent2() {
		
		System.out.println("KLIJENT 2 KRENUO DA KREIRA PRVU OCENU ZA LEKARA");
		Pacijent pacijent1 = this.pacijentService.getOne(1);
		OcenaParamDTO ocena = new OcenaParamDTO();
		ocena.setId(10);
		ocena.setOcena(10);
		try {
			this.lekarService.ocenjivanje(pacijent1, ocena);
			System.out.println("KLIJENT 2 USPESNO PRVI PUT OCENIO LEKARA!");
		}
		catch(Exception e) {
			System.out.println("KLIJENT 2 ZAKASNIO DA PRVI PUT OCENI LEKARA!");
		}
		System.out.println("KLIJENT 2 ZAVSIO SA RADOM");
		
	}

	
}
