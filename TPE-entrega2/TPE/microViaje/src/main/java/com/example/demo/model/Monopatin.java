package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@SuppressWarnings("unused")
public class Monopatin {
	private long id;
	private double latitud;
	private double longitud;
	private int tiempoUso;
	private String estado;
	private int kilometrosTotales;
	private boolean habilitado;
	
	public Monopatin(long id, double latitud, double longitud, int tiempo, String estado, int kilometros, boolean habilitado) {
		this.id=id;
		this.latitud=latitud;
		this.longitud=longitud;
		this.tiempoUso=tiempo;
		this.estado=estado;
		this.kilometrosTotales=kilometros;
		this.habilitado=habilitado;
	}
	
	public long getId() {
		return id;
	}
}