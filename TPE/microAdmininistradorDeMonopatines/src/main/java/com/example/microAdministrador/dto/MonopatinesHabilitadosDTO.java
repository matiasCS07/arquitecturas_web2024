package com.example.microAdministrador.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class MonopatinesHabilitadosDTO {
	private List<Long> monopatinesHabilitados;
	private List<Long> monopatinesNoHabilitados;
	private int cantidadHabilitados;
	private int cantidadNoHabilitados;
	
	public List<Long> getMonopatinesHabilitados() {
		return monopatinesHabilitados;
	}
	public void setMonopatinesHabilitados(List<Long> monopatinesHabilitados) {
		this.monopatinesHabilitados = monopatinesHabilitados;
	}
	public List<Long> getMonopatinesNoHabilitados() {
		return monopatinesNoHabilitados;
	}
	public void setMonopatinesNoHabilitados(List<Long> monopatinesNoHabilitados) {
		this.monopatinesNoHabilitados = monopatinesNoHabilitados;
	}
	public int getCantidadHabilitados() {
		return cantidadHabilitados;
	}
	public void setCantidadHabilitados(int cantidadHabilitados) {
		this.cantidadHabilitados = cantidadHabilitados;
	}
	public int getCantidadNoHabilitados() {
		return cantidadNoHabilitados;
	}
	public void setCantidadNoHabilitados(int cantidadNoHabilitados) {
		this.cantidadNoHabilitados = cantidadNoHabilitados;
	}
}
