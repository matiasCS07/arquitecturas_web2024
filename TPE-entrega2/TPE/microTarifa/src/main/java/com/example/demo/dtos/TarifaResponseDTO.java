package com.example.demo.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaResponseDTO {
	private Long tarifa;
	private LocalDate fechaInicio;
	
	
	public Long getTarifa() {
		return tarifa;
	}
	public void setTarifa(Long tarifa) {
		this.tarifa = tarifa;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
}
