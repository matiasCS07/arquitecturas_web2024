package com.example.demo.dto;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class MonopatinReporteDTO {
	private long id_monopatin;
	private Duration tiempoUso;
	private int kilometrosTotales;
	private String estado;
	private boolean habilitado;
	private double latitud;
	private double longitud;
	
	
	public long getId_monopatin() {
		return id_monopatin;
	}
	public void setId_monopatin(long id_monopatin) {
		this.id_monopatin = id_monopatin;
	}
	public Duration getTiempoUso() {
		return tiempoUso;
	}
	public void setTiempoUso(Duration tiempoUso) {
		this.tiempoUso = tiempoUso;
	}
	public int getKilometrosTotales() {
		return kilometrosTotales;
	}
	public void setKilometrosTotales(int kilometrosTotales) {
		this.kilometrosTotales = kilometrosTotales;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
}
