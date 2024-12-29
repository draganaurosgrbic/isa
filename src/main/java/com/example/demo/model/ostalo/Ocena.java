package com.example.demo.model.ostalo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.demo.model.korisnici.Pacijent;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"pacijent", "provera"})})
public class Ocena {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = false, nullable = false)
	private int vrednost;
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;
	@Column(unique = false, nullable = false)
	private String provera;
	
	public Ocena() {
		super();
	}

	public Ocena(int vrednost, Pacijent pacijent, String provera) {
		super();
		this.vrednost = vrednost;
		this.pacijent = pacijent;
		this.provera = provera;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVrednost() {
		return vrednost;
	}

	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public String getProvera() {
		return provera;
	}

	public void setProvera(String provera) {
		this.provera = provera;
	}
	
}
