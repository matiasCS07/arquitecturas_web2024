package com.example.demo.dtos;

public class CarreraDTO {
	private Integer idCarrera;
	private String nombreCarrera;
	private int inscripciones;
	
	public CarreraDTO() {
		super();
	}
	
	public CarreraDTO(Integer id, String nombre, int inscripciones) {
		this.idCarrera=id;
		this.nombreCarrera=nombre;
		this.inscripciones=inscripciones;
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

	public int getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(int inscripciones) {
		this.inscripciones = inscripciones;
	}
}
