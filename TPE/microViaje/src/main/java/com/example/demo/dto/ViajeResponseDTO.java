package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeResponseDTO {
	private long idViaje;
	private long idUsuario;
	private long idMonopatin;
	private LocalDateTime inicio;
	private LocalDateTime finalizcion;
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
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setIdMonopatin(long idMonopatin) {
		this.idMonopatin = idMonopatin;
	}
	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}
	public void setFinalizcion(LocalDateTime finalizcion) {
		this.finalizcion = finalizcion;
	}
	public long getIdViaje() {
		return idViaje;
	}
	public LocalDateTime getInicio() {
		return inicio;
	}
	public LocalDateTime getFinalizcion() {
		return finalizcion;
	}
	public void setIdViaje(long idViaje) {
		this.idViaje = idViaje;
	}
}