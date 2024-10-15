package com.example.demo.dtos;

public class ReporteDTO {
	private String nombre_carrera;
	private int anio;
	private int egresados;
	
	public ReporteDTO() {
		super();
	}
	
	public ReporteDTO(String carrera, int anio, int egresados) {
		this.nombre_carrera=carrera;
		this.anio=anio;
		this.egresados=egresados;
	}

	public String getNombre_carrera() {
		return nombre_carrera;
	}

	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getEgresados() {
		return egresados;
	}

	public void setEgresados(int egresados) {
		this.egresados = egresados;
	}
}
