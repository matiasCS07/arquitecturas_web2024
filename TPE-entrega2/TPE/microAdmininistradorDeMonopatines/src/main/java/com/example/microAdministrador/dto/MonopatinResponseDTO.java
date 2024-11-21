package com.example.microAdministrador.dto;

import java.time.Duration;

import com.example.microAdministrador.model.Monopatin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class MonopatinResponseDTO {
	private long id;
	private double longitud;
	private double latitud;
	private int kilometrosTotales;
	private String estado;
	private boolean habilitado;
	private Duration tiempoUso;
    private String mensaje;
    private boolean exito;
    
	public int getKilometrosTotales() {
		return kilometrosTotales;
	}

	public String getEstado() {
		return estado;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public Duration getTiempoUso() {
		return tiempoUso;
	}

	public String getMensaje() {
		return mensaje;
	}

	public boolean isExito() {
		return exito;
	}
    
    public long getId() {
    	return id;
    }

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setKilometrosTotales(int kilometrosTotales) {
		this.kilometrosTotales = kilometrosTotales;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public void setTiempoUso(Duration tiempoUso) {
		this.tiempoUso = tiempoUso;
	}
}
