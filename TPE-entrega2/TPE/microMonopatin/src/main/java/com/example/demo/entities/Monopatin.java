package com.example.demo.entities;

import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Monopatin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_monopatin;
	@Column(name="tiempo_uso")
	private Duration tiempoUso;
	@Column(name="kilometros_totales")
	private int kilometrosTotales;
	@Column
	private String estado;
	@Column
	private boolean habilitado;
	@Column(name="ubicacion_latitud")
	private double latitud;
	@Column(name="ubicacion_longitud")
	private double longitud;
	
	public Monopatin(String estado, double latitud, double longitud) {
		this.setEstado(estado);
		this.setLongitud(longitud);
		this.setLatitud(latitud);
		this.setKilometrosTotales(0);
		this.setHabilitado(true);
		this.setTiempoUso(Duration.ZERO);
	}
	
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
