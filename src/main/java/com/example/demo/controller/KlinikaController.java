package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conversion.total.KlinikaConversion;
import com.example.demo.dto.model.KlinikaDTO;
import com.example.demo.dto.pretraga.BolestDTO;
import com.example.demo.dto.pretraga.KlinikaPretragaDTO;
import com.example.demo.dto.pretraga.KlinikaSlobodnoDTO;
import com.example.demo.dto.pretraga.PeriodDTO;
import com.example.demo.dto.unos.OcenaParamDTO;
import com.example.demo.dto.unos.PretragaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/klinika")
public class KlinikaController {

	@Autowired
	private KlinikaService klinikaService;

	@Autowired
	private KlinikaConversion klinikaConversion;

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value = "/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaDTO>> pregled(){
		try {
			return new ResponseEntity<>(this.klinikaConversion.get(this.klinikaService.findAll()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody KlinikaDTO klinikaDTO) {
		try {
			this.klinikaService.save(this.klinikaConversion.get(klinikaDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody KlinikaDTO klinikaDTO){
		try {
			this.klinikaService.save(this.klinikaConversion.get(klinikaDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KlinikaDTO> profil(){
		try {
			Admin admin = (Admin) userService.getSignedKorisnik();
			return new ResponseEntity<>(this.klinikaConversion.get(admin.getKlinika()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value = "/ocenjivanje/{posetaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BolestDTO> ocenjivanje(@PathVariable Integer posetaId, @RequestBody OcenaParamDTO param){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(new BolestDTO(this.klinikaService.ocenjivanje(pacijent, param, posetaId)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value = "/slobodno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaSlobodnoDTO>> slobodno(){
		try {
			return new ResponseEntity<>(this.klinikaConversion.getSlobodno(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/naziv", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> naziv(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
					
			return new ResponseEntity<>(admin.getKlinika().getNaziv(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaPretragaDTO>> pretraga(){
		try {
			return new ResponseEntity<>(this.klinikaConversion.getPretraga(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaPretragaDTO>> pretraga(@RequestBody PretragaDTO param){
		try {
			return new ResponseEntity<>(this.klinikaService.pretraga(param), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/ocena", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> ocena(){
		try {
			Admin admin = (Admin) userService.getSignedKorisnik();
			return new ResponseEntity<>(admin.getKlinika().prosecnaOcena(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value="/admin/profit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> profit(@RequestBody PeriodDTO period){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			double profit = admin.getKlinika().getProfit(period.getPocetak(), period.getKraj()); 
			return new ResponseEntity<>(profit + " din", HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/graf/{parametar}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Integer>> graf(@PathVariable String parametar){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(admin.getKlinika().podaciGraf(parametar), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
