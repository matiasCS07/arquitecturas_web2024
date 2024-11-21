package com.example.demo.dto;


import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class MonopatinResponseDTO {
	private long id;
	private double latitud;
	private double longitud;
	private int kilometrosTotales;
	private String estado;
	private boolean habilitado;
	private Duration tiempoUso;
    private String mensaje;
    private boolean exito;
    

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

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public long getId() {
    	return id;
    }

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
}
