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

import com.example.demo.conversion.partial.KartonConversion;
import com.example.demo.conversion.partial.PacijentConversion;
import com.example.demo.dto.model.KartonDTO;
import com.example.demo.dto.model.PacijentDTO;
import com.example.demo.dto.pretraga.BolestDTO;
import com.example.demo.dto.pretraga.TerminDTO;
import com.example.demo.dto.pretraga.ZahtevTerminDTO;
import com.example.demo.dto.unos.ParamDTO;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.posete.Karton;
import com.example.demo.service.PacijentService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/pacijent")
public class PacijentController {
							
	@Autowired
	private PacijentService pacijentService;

	@Autowired
	private PacijentConversion pacijentConversion;

	@Autowired
	private KartonConversion kartonConversion;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PacijentDTO> profil(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.pacijentConversion.get(pacijent), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody PacijentDTO pacijentDTO){
		try {
			this.pacijentService.save(this.pacijentConversion.get(pacijentDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/karton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KartonDTO> karton(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.kartonConversion.get(pacijent.getKarton()), HttpStatus.OK);		
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/bolesti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BolestDTO>> bolesti(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			Karton karton = pacijent.getKarton();
			return new ResponseEntity<>(karton.getBolesti(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/termini", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TerminDTO>> termini(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			Karton karton = pacijent.getKarton();		
			return new ResponseEntity<>(karton.getTermini(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/zahtevTermini", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZahtevTerminDTO>> zahtevTermini(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			Karton karton = pacijent.getKarton();		
			return new ResponseEntity<>(karton.getZahtevTermini(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/aktiviranje/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> aktiviranje(@RequestBody ParamDTO param){
		try {
			boolean retval = this.pacijentService.aktiviranje(param.getParam());
			if (retval)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
