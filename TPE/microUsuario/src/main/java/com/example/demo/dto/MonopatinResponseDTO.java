package com.example.demo.dto;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class MonopatinResponseDTO {
	private long id;
	private Point ubicacion;
	private int kilometrosTotales;
	private String estado;
	private boolean habilitado;
	private int tiempoUso;
    private String mensaje;
    private boolean exito;
    
    public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
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

	public void setTiempoUso(int tiempoUso) {
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
}
