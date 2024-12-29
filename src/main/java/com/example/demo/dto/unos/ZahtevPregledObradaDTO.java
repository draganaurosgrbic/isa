package com.example.demo.dto.unos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.demo.model.zahtevi.ZahtevPoseta;

public class ZahtevPregledObradaDTO {
	
	private Integer id;
	private boolean pregled;
	private String datum;
	private String naziv;
	private String lekar;
	private String pacijent;
	private Integer idTipa;
	private Integer idSale;
	private Integer idLekar;
	private Integer idPacijent;
	private int sati;
	private int minuti;
	private String kraj;
	private String lekarPocetak;
	private String lekarKraj;
	
	public ZahtevPregledObradaDTO() {
		super();
	}
	
	public ZahtevPregledObradaDTO(ZahtevPoseta zahtev) {
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		GregorianCalendar gc = new GregorianCalendar();
		String radnoPocetak = f.format(zahtev.getLekar().getPocetnoVreme());
		String radnoKraj = f.format(zahtev.getLekar().getKrajnjeVreme());
		
		this.id = zahtev.getId();
		this.pregled = zahtev.getTipPosete() != null ? zahtev.getTipPosete().isPregled() : null;
		this.datum = f.format(zahtev.getDatum());
		this.naziv = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getNaziv() : null;
		this.lekar = zahtev.getLekar().getIme()+" "+zahtev.getLekar().getPrezime();
		this.pacijent = zahtev.getKarton().getPacijent().getIme()+" "+zahtev.getKarton().getPacijent().getPrezime();
		this.idTipa = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getId() : null;
		this.idLekar = zahtev.getLekar().getId();
		this.idPacijent = zahtev.getKarton().getPacijent().getId();
		gc.setTime(zahtev.getDatum());
        gc.add(Calendar.HOUR_OF_DAY, zahtev.getTipPosete().getSati());
        this.sati = zahtev.getTipPosete().getSati();
        gc.add(Calendar.MINUTE, zahtev.getTipPosete().getMinute());
        this.minuti = zahtev.getTipPosete().getMinute();
        this.kraj = f.format(gc.getTime());
        this.lekarPocetak = radnoPocetak.substring(radnoPocetak.length() - 5);
        this.lekarKraj = radnoKraj.substring(radnoKraj.length() - 5);
	}

	public void osveziKraj() throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(f.parse(this.datum));
        gc.add(Calendar.HOUR_OF_DAY, this.sati);
        gc.add(Calendar.MINUTE, this.minuti);
        this.kraj = f.format(gc.getTime());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}

	public Integer getIdTipa() {
		return idTipa;
	}

	public void setIdTipa(Integer idTipa) {
		this.idTipa = idTipa;
	}

	public Integer getIdSale() {
		return idSale;
	}

	public void setIdSale(Integer idSale) {
		this.idSale = idSale;
	}

	public Integer getIdLekar() {
		return idLekar;
	}

	public void setIdLekar(Integer idLekar) {
		this.idLekar = idLekar;
	}

	public Integer getIdPacijent() {
		return idPacijent;
	}

	public void setIdPacijent(Integer idPacijent) {
		this.idPacijent = idPacijent;
	}

	public int getSati() {
		return sati;
	}

	public void setSati(int sati) {
		this.sati = sati;
	}

	public int getMinuti() {
		return minuti;
	}

	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getLekarPocetak() {
		return lekarPocetak;
	}

	public void setLekarPocetak(String lekarPocetak) {
		this.lekarPocetak = lekarPocetak;
	}

	public String getLekarKraj() {
		return lekarKraj;
	}

	public void setLekarKraj(String lekarKraj) {
		this.lekarKraj = lekarKraj;
	}
	
}
