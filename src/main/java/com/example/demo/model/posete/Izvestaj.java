package com.example.demo.model.posete;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.example.demo.model.resursi.Dijagnoza;

@Entity
public class Izvestaj {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = false, nullable = true)
	private String opis;
	@OneToOne
	@JoinColumn(name="poseta")
	private Poseta poseta;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "izvestaj_dijagnoza",
    joinColumns = @JoinColumn(name = "izvestaj"),
    inverseJoinColumns = @JoinColumn(name = "dijagnoza"))
	private Set<Dijagnoza> dijagnoze = new HashSet<>();
	@OneToOne
	@JoinColumn(name="terapija")
	private Terapija terapija;
	
	public Izvestaj() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Poseta getPoseta() {
		return poseta;
	}

	public void setPoseta(Poseta poseta) {
		this.poseta = poseta;
	}

	public Set<Dijagnoza> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Terapija getTerapija() {
		return terapija;
	}

	public void setTerapija(Terapija terapija) {
		this.terapija = terapija;
	}
	
}
