package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Carrera {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCarrera;
	
	@Column
	private String nombreCarrera;
	
	@OneToMany(mappedBy="carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Inscripcion> inscripciones;
	
	public Carrera() {
		super();
	}
	
	public Carrera(String nombre, List<Inscripcion>inscripciones) {
		this.nombreCarrera=nombre;
		this.inscripciones=new ArrayList<>(inscripciones);
	}

	public Integer getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
}
