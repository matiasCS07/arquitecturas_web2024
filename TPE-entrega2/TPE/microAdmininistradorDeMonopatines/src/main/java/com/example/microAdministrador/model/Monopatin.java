package com.example.microAdministrador.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
@SuppressWarnings("unused")
public class Monopatin {
	@Id
	private long id;
	private double latitud;
	private double longitud;
	private Duration tiempoUso;
	private String estado;
	private int kilometrosTotales;
	private boolean habilitado;
	
	public Monopatin(long id, double latitud, double longitud, Duration tiempo, String estado, int kilometros, boolean habilitado) {
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
