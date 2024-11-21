package com.example.microAdministrador.model;

import java.time.LocalDate;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {
	@Id
	private long id;
	private Long precio;
	private LocalDate fechaInicio;
	
	public Long getPrecio() {
		return precio;
	}
	public void setPrecio(Long precio) {
		this.precio = precio;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
