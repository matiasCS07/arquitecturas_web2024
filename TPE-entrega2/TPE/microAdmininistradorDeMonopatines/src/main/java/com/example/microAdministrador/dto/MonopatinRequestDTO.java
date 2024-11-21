package com.example.microAdministrador.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinRequestDTO {
	
    @NotEmpty(message = "el campo latitud del monopatin no puede estar vacio")
    @NotNull( message = "La latitud es un campo obligatorio.")
    private double latitud;
    
    @NotEmpty(message = "el campo longitud del monopatin no puede estar vacio")
    @NotNull( message = "La longitud es un campo obligatorio.")
    private double longitud;

    @NotNull( message = "El estado es un campo obligatorio.")
    @NotEmpty(message = "el campo estado del monopatin no puede estar vacio")
    private String estado;

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}