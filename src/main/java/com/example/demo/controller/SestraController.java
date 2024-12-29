package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.example.demo.conversion.total.SestraConversion;
import com.example.demo.dto.model.SestraDTO;
import com.example.demo.dto.pretraga.PacijentPretragaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.korisnici.Sestra;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
import com.example.demo.service.SestraService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/sestra")
public class SestraController {
	
	@Autowired
	private SestraService sestraService;
	
	@Autowired
	private SestraConversion sestraConversion;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ApplicationName name;
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SestraDTO>> pregled(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.sestraConversion.get(this.sestraService.findAll(admin)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody SestraDTO sestraDTO) {
		Sestra sestra;
		try {
			sestra = this.sestraConversion.get(sestraDTO);
			this.sestraService.save(sestra);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String obavestenje = "Uspesno ste registrovani kao medicinska sestra klinike " + sestra.getKlinika().getNaziv() + "\n"
					+ "lozinka: " + sestraDTO.getLozinka() + "\nLink za prijavu: " + this.name.getName();
			Message poruka = new Message(sestra.getEmail(), "Registracija medicinske sestre klinike", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('Sestra')")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SestraDTO> profil(){
		try {
			Sestra sestra = (Sestra) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.sestraConversion.get(sestra), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Sestra')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody SestraDTO sestraDTO){
		try {
			this.sestraService.save(this.sestraConversion.get(sestraDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping(value = "/brisanje/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> brisanje(@PathVariable Integer id){
		try {
			this.sestraService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
	}
	
	@PreAuthorize("hasAuthority('Sestra')")
	@GetMapping(value="/pacijenti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PacijentPretragaDTO>> pacijenti(){
		try {
			Sestra sestra = (Sestra) this.userService.getSignedKorisnik();
			List<PacijentPretragaDTO> pacijentiPretraga = new ArrayList<>();
			for (Pacijent p: this.sestraService.pacijentiKlinike(sestra))
				pacijentiPretraga.add(new PacijentPretragaDTO(p, sestra));
			return new ResponseEntity<>(pacijentiPretraga, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
