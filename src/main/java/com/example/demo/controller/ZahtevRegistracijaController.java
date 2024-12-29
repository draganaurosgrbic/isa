package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conversion.total.PasswordEncoder;
import com.example.demo.conversion.total.ZahtevRegistracijaConversion;
import com.example.demo.dto.model.ZahtevRegistracijaDTO;
import com.example.demo.dto.unos.ZahtevRegistracijaObradaDTO;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.zahtevi.ZahtevRegistracija;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
import com.example.demo.service.ZahtevRegistracijaService;

@RestController
@RequestMapping(value = "/zahtevRegistracija")
public class ZahtevRegistracijaController {
	
	@Autowired
	private ZahtevRegistracijaService zahtevRegistracijaService;
	
	@Autowired
	private ZahtevRegistracijaConversion zahtevRegistracijaConversion;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ApplicationName name;
	
	@Autowired
	private PasswordEncoder encoder;

	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value = "/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZahtevRegistracijaDTO>> pregled(){
		try {
			return new ResponseEntity<>(this.zahtevRegistracijaConversion.get(this.zahtevRegistracijaService.findAll()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody ZahtevRegistracijaDTO zahtevDTO) {
		try {
			this.zahtevRegistracijaService.save(this.zahtevRegistracijaConversion.get(zahtevDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/potvrda", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> potvrda(@RequestBody ZahtevRegistracijaObradaDTO obradaZahteva) {
		Pacijent pacijent;
		try {
			ZahtevRegistracija zahtev = this.zahtevRegistracijaService.getOne(obradaZahteva.getId());
			pacijent = this.zahtevRegistracijaService.potvrdi(zahtev);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String obavestenje = "Uspesno ste registrovani kao pacijent klinickog centra 'POSLEDNJI TRZAJ'. Molimo Vas da "
					+ "aktivirate svoj nalog klikom na link: \n" + this.name.getName() + "/#/aktiviranjeNaloga?id=" + this.encoder.encoder().encode(pacijent.getId() + "");
			Message poruka = new Message(pacijent.getEmail(), "Registracija uspesna", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/odbijanje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> odbijanje(@RequestBody ZahtevRegistracijaObradaDTO obradaZahteva) {
		ZahtevRegistracija zahtev;
		try {
			zahtev = this.zahtevRegistracijaService.getOne(obradaZahteva.getId());
			this.zahtevRegistracijaService.delete(zahtev.getId());
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String obavestenje = "Vasa registracija je odbijena iz sledecih razloga: \n" + obradaZahteva.getRazlog() + ""
					+ "\nMolimo Vas da pokusate ponovo. \n";
			Message poruka = new Message(zahtev.getEmail(), "Registracija odbijena", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
}
