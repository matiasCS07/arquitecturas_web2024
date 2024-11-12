package com.example.demo.model;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Monopatin {
	private long id;
	private double latitud;
	private double longitud;
	private int tiempoUso;
	private String estado;
	private int kilometrosTotales;
	private boolean habilitado;
	
	public Monopatin(long id, Point ubicacion, int tiempo, String estado, int kilometros, boolean habilitado) {
		this.id=id;
		this.latitud=ubicacion.getY();
		this.longitud=ubicacion.getX();
		this.tiempoUso=tiempo;
		this.estado=estado;
		this.kilometrosTotales=kilometros;
		this.habilitado=habilitado;
	}
	
	public long getId() {
		return id;
	}
}