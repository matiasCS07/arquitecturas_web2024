package com.example.microAdministrador.dto;

import java.util.List;

import com.example.microAdministrador.entities.Tarifa;
import com.example.microAdministrador.model.Monopatin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorDTO {
	private long id;
	private List<Monopatin> monopatines;
	private List<Double[]> paradas;
	private List<Tarifa> tarifa;
	private List<Tarifa> tarifaExtra;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Monopatin> getMonopatines() {
		return monopatines;
	}
	public void setMonopatines(List<Monopatin> monopatines) {
		this.monopatines = monopatines;
	}
	public List<Double[]> getParadas() {
		return paradas;
	}
	public void setParadas(List<Double[]> paradas) {
		this.paradas = paradas;
	}
	public List<Tarifa> getTarifa() {
		return tarifa;
	}
	public void setTarifa(List<Tarifa> tarifa) {
		this.tarifa = tarifa;
	}
	public List<Tarifa> getTarifaExtra() {
		return tarifaExtra;
	}
	public void setTarifaExtra(List<Tarifa> tarifaExtra) {
		this.tarifaExtra = tarifaExtra;
	}
}
