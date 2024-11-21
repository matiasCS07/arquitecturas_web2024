package com.example.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="tarifa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private Long precio;
	
	@Column(name="fecha_vigencia")
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
}

