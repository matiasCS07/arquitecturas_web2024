package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeFinalizadoDTO {
	private long idViaje;
	private LocalDateTime inicio;
	private int pausa;
	private LocalDateTime finalizacion;
	private int costo;
	private String mensaje;
	private boolean exito;
	
	public long getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(long idViaje) {
		this.idViaje = idViaje;
	}
	public LocalDateTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}
	public int getPausa() {
		return pausa;
	}
	public void setPausa(int pausa) {
		this.pausa = pausa;
	}
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
	public LocalDateTime getFinalizacion() {
		return finalizacion;
	}
	public void setFinalizacion(LocalDateTime finalizacion) {
		this.finalizacion = finalizacion;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
}
