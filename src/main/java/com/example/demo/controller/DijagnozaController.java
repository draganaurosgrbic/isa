package com.example.demo.controller;

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

import com.example.demo.conversion.total.DijagnozaConversion;
import com.example.demo.dto.model.DijagnozaDTO;
import com.example.demo.service.DijagnozaService;

@RestController
@RequestMapping(value = "/dijagnoza")
public class DijagnozaController {
	
	@Autowired
	private DijagnozaService dijagnozaService;
	
	@Autowired
	private DijagnozaConversion dijagnozaConversion;
	
	@PreAuthorize("hasAnyAuthority('Lekar','SuperAdmin')")
	@GetMapping(value = "/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DijagnozaDTO>> pregled(){
		try {
			return new ResponseEntity<>(this.dijagnozaConversion.get(this.dijagnozaService.findAll()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody DijagnozaDTO dijagnozaDTO) {
		try {
			this.dijagnozaService.save(this.dijagnozaConversion.get(dijagnozaDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@DeleteMapping(value = "/brisanje/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> brisanje(@PathVariable Integer id){
		try {
			this.dijagnozaService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
