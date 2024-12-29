package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conversion.partial.TerapijaConversion;
import com.example.demo.dto.model.TerapijaDTO;
import com.example.demo.model.korisnici.Sestra;
import com.example.demo.service.TerapijaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/terapija")
public class TerapijaController {
	
	@Autowired
	private TerapijaService terapijaService;
	
	@Autowired
	private TerapijaConversion terapijaConversion;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('Sestra')")
	@GetMapping(value = "/neovereno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TerapijaDTO>> neovereno(){
		try {
			Sestra sestra = (Sestra) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.terapijaConversion.get(this.terapijaService.neovereno(sestra)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Sestra')")
	@GetMapping(value="/overi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> overi(@PathVariable Integer id){
		try {
			Sestra sestra = (Sestra) this.userService.getSignedKorisnik();
			this.terapijaService.overi(id, sestra);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
