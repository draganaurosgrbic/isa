package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.example.demo.conversion.partial.IzvestajConversion;
import com.example.demo.conversion.partial.KartonConversion;
import com.example.demo.conversion.total.LekarConversion;
import com.example.demo.dto.model.IzvestajDTO;
import com.example.demo.dto.model.KartonDTO;
import com.example.demo.dto.model.LekarDTO;
import com.example.demo.dto.pretraga.ObavezaDTO;
import com.example.demo.dto.pretraga.PacijentPretragaDTO;
import com.example.demo.dto.unos.OcenaParamDTO;
import com.example.demo.dto.unos.ZahtevPregledObradaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.posete.KrvnaGrupa;
import com.example.demo.service.EmailService;
import com.example.demo.service.IzvestajService;
import com.example.demo.service.KartonService;
import com.example.demo.service.LekarService;
import com.example.demo.service.Message;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/lekar")
public class LekarController {
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private LekarConversion lekarConversion;
		
	@Autowired
	private KartonService kartonService;
	
	@Autowired
	private KartonConversion kartonConversion;
		
	@Autowired
	private IzvestajService izvestajService;
	
	@Autowired
	private IzvestajConversion izvestajConversion;
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ApplicationName name;
			
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LekarDTO>> pregled(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.lekarConversion.get(this.lekarService.findAll(admin)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody LekarDTO lekarDTO) {
		Lekar lekar;
		try {
			lekar = this.lekarConversion.get(lekarDTO);
			this.lekarService.save(lekar);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String obavestenje = "Uspesno ste registrovani kao lekar klinike " + lekar.getKlinika().getNaziv() + "\n"
					+ "lozinka: " + lekarDTO.getLozinka() + "\nLink za prijavu: " + this.name.getName();
			Message poruka = new Message(lekar.getEmail(), "Registracija lekara klinike", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}	
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LekarDTO> profil(){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.lekarConversion.get(lekar), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody LekarDTO lekarDTO){
		try {
			this.lekarService.save(this.lekarConversion.get(lekarDTO));
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
			this.lekarService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value = "/ocenjivanje/{posetaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LekarDTO> ocenjivanje(@PathVariable Integer posetaId, @RequestBody OcenaParamDTO param){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(new LekarDTO(this.lekarService.ocenjivanje(pacijent, param)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/obaveze", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObavezaDTO>> getObaveze(){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(lekar.getObaveze(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/pacijenti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PacijentPretragaDTO>> getPacijenteLekara(){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			List<PacijentPretragaDTO> pacijentiPretraga = new ArrayList<>();
			for (Pacijent p: this.lekarService.pacijentiLekara(lekar))
				pacijentiPretraga.add(new PacijentPretragaDTO(p, lekar));
			return new ResponseEntity<>(pacijentiPretraga, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/krvneGrupe", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KrvnaGrupa[]> getKrvneGrupe(){
		try {
			return new ResponseEntity<>(KrvnaGrupa.values(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value="/izmenaKartona", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmenaKartona(@RequestBody KartonDTO kartonDTO){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			
			if (lekar.getZapocetaPoseta() == null)
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			
			if (!lekar.getZapocetaPoseta().getKarton().getPacijent().getId().equals(kartonDTO.getPacijent()))
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			
			this.kartonService.save(this.kartonConversion.get(kartonDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value = "/izmenaIzvestaja", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmenaIzvestaja(@RequestBody IzvestajDTO izvestajDTO) {
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			this.izvestajService.save(this.izvestajConversion.get(izvestajDTO, lekar));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/admin/slobodni", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LekarDTO>> slobodni(@RequestBody ZahtevPregledObradaDTO zahtev) {
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
			zahtev.osveziKraj();
			Date pocetak = f.parse(zahtev.getDatum());
			Date kraj = f.parse(zahtev.getKraj());
			List<LekarDTO> retval = new ArrayList<>();
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			
			for (Lekar lekar : this.lekarService.findAll(admin)) {
				if (lekar.slobodan(pocetak, kraj) && lekar.slobodanZahtev(pocetak, kraj, zahtev.getId()))
					retval.add(this.lekarConversion.get(lekar));
			}
			
			return new ResponseEntity<>(retval, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
