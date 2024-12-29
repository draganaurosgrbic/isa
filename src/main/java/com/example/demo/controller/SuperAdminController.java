package com.example.demo.controller;

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

import com.example.demo.conversion.total.SuperAdminConversion;
import com.example.demo.dto.model.SuperAdminDTO;
import com.example.demo.model.korisnici.SuperAdmin;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
import com.example.demo.service.SuperAdminService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/superAdmin")
public class SuperAdminController {

	@Autowired
	private SuperAdminService superAdminService;
	
	@Autowired
	private SuperAdminConversion superAdminConversion;
		
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
		
	@Autowired
	private ApplicationName name;

	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody SuperAdminDTO superAdminDTO) {
		SuperAdmin superAdmin;
		try {
			superAdmin = this.superAdminConversion.get(superAdminDTO);
			this.superAdminService.save(superAdmin);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String obavestenje = "Uspesno ste registrovani kao super admin klinickog centra 'POSLEDNJI TRZAJ' \n"
					+ "lozinka: " + superAdminDTO.getLozinka() + "\nLink za prijavu: " + this.name.getName();
			Message poruka = new Message(superAdmin.getEmail(), "Registracija super admina klinickog centra", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}	
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperAdminDTO> profil(){
		try {
			SuperAdmin superAdmin = (SuperAdmin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.superAdminConversion.get(superAdmin), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody SuperAdminDTO superAdminDTO){
		try {
			this.superAdminService.save(this.superAdminConversion.get(superAdminDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
