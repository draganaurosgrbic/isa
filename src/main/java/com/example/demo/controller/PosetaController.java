package com.example.demo.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conversion.partial.IzvestajConversion;
import com.example.demo.conversion.partial.PosetaConversion;
import com.example.demo.dto.model.IzvestajDTO;
import com.example.demo.dto.unos.PredefinisanaPosetaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.posete.Poseta;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
import com.example.demo.service.PosetaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/poseta")
public class PosetaController {
		
	@Autowired
	private PosetaService posetaService;
	
	@Autowired
	private PosetaConversion posetaConversion;
		
	@Autowired
	private IzvestajConversion izvestajConversion;
	
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	private final SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody PredefinisanaPosetaDTO pregled) {
		try {
			this.posetaService.save(this.posetaConversion.get(pregled), null);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
				
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value = "/zakazi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> zakazi(@PathVariable Integer id){

		Pacijent pacijent;
		Poseta poseta;
		try {
			pacijent = (Pacijent) this.userService.getSignedKorisnik();
			poseta = this.posetaService.getOne(id);
			this.posetaService.zakazi(id, pacijent.getKarton());
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			String obavestenje = "Pregled tipa " + poseta.getTipPosete().getNaziv() + " datuma "
					+ this.f.format(poseta.getDatum()) + " uspesno zakazan. ";
			Message poruka = new Message(pacijent.getEmail(), "Pregled zakazan", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAnyAuthority('Lekar','Pacijent')")
	@DeleteMapping(value="/otkazi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> otkazi(@PathVariable Integer id){	
		Poseta poseta;
		try {
			poseta = this.posetaService.otkazi(id);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String obavestenje = "Poseta zakazana za " + this.f.format(poseta.getDatum()) + " je otkazana. ";
			for (Lekar l: poseta.getLekari())
				this.emailService.sendMessage(new Message(l.getEmail(), "Poseta otkazana", obavestenje));
			this.emailService.sendMessage(new Message(poseta.getKarton().getPacijent().getEmail(), "Poseta otkazana", obavestenje));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}	
	}

	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value = "/zapoceto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> zapoceto() {
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			if (lekar.getZapocetaPoseta() == null)
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			return new ResponseEntity<>(lekar.getZapocetaPoseta().getKarton().getId(), HttpStatus.OK);

		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/zapocni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> zapocni(@PathVariable Integer id){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			this.posetaService.zapocni(id, lekar);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value = "/zavrsi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> zavrsi(@RequestBody IzvestajDTO izvestajDTO) {
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			this.posetaService.zavrsi(this.izvestajConversion.get(izvestajDTO, lekar), lekar);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
