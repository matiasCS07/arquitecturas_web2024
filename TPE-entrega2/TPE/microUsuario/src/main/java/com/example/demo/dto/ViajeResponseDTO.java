package com.example.demo.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class ViajeResponseDTO {
	private long idViaje;
	private long idUsuario;
	private long idMonopatin;
	private LocalDateTime inicio;
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
	public long getIdUsuario() {
		return idUsuario;
	}
	public long getIdMonopatin() {
		return idMonopatin;
	}
	public long getIdViaje() {
		return idViaje;
	}
}
