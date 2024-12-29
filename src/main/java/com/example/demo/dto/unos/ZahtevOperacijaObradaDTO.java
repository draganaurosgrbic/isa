package com.example.demo.dto.unos;

import java.util.List;

public class ZahtevOperacijaObradaDTO {
	
	private Integer id;
	private boolean pregled;
	private Integer tipId;
	private Integer salaId;
	private List<Integer> lekariId;
	private Integer pacijentId;
	private String pocetak;
	private String kraj;
	private String pocetakOriginalni;

	public ZahtevOperacijaObradaDTO() {
		super();
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

	public Integer getTipId() {
		return tipId;
	}

	public void setTipId(Integer tipId) {
		this.tipId = tipId;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public List<Integer> getLekariId() {
		return lekariId;
	}

	public void setLekariId(List<Integer> lekariId) {
		this.lekariId = lekariId;
	}

	public Integer getPacijentId() {
		return pacijentId;
	}

	public void setPacijentId(Integer pacijentId) {
		this.pacijentId = pacijentId;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getPocetakOriginalni() {
		return pocetakOriginalni;
	}

	public void setPocetakOriginalni(String pocetakOriginalni) {
		this.pocetakOriginalni = pocetakOriginalni;
	}
	
}
