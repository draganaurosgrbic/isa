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

import com.example.demo.conversion.total.AdminConversion;
import com.example.demo.dto.model.AdminDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminConversion adminConversion;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ApplicationName name;
		
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody AdminDTO adminDTO) {
		Admin admin;
		try {
			admin = this.adminConversion.get(adminDTO);
			this.adminService.save(admin);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String obavestenje = "Uspesno ste registrovani kao administrator klinike " + admin.getKlinika().getNaziv() + "\n"
					+ "lozinka: " + adminDTO.getLozinka() + "\nLink za prijavu: " + this.name.getName();
			Message poruka = new Message(admin.getEmail(), "Registracija administratora klinike", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminDTO> profil(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.adminConversion.get(admin), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody AdminDTO adminDTO){
		try {
			this.adminService.save(this.adminConversion.get(adminDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
