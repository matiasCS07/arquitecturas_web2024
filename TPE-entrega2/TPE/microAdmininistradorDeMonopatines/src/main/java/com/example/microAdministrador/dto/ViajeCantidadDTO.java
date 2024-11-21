package com.example.microAdministrador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeCantidadDTO {
	private long idMonopatin;
	private int cant_viajes;
	private String mensaje;
	private boolean exito;
	
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean isExito() {
		return exito;
	}
	public void setExito(boolean exito) {
		this.exito = exito;
	}
	public long getIdMonopatin() {
		return idMonopatin;
	}
	public void setIdMonopatin(long idMonopatin) {
		this.idMonopatin = idMonopatin;
	}
	public int getCant_viajes() {
		return cant_viajes;
	}
	public void setCant_viajes(int cant_viajes) {
		this.cant_viajes = cant_viajes;
	}
	
}