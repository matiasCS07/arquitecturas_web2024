package com.example.microAdministrador.entities;

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
public class Tarifa { //consideré que, siendo la entidad tarifa dependiente del servicio y del administrador, que se mantenga en el mismo microservicio que el administrador.
						//toda entrada y salida de datos, y cualquier interaccion con la tarifa, sera mediada a traves del administrador
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
