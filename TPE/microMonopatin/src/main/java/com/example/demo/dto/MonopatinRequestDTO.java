package com.example.demo.dto;

import com.example.demo.entities.Monopatin;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Data
@Getter
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
    
    public MonopatinRequestDTO(Monopatin m) {
    	this.latitud=m.getLatitud();
    	this.longitud=m.getLongitud();
    	this.estado=m.getEstado();
    }


	public String getEstado() {
		return estado;
	}


	public double getLatitud() {
		return latitud;
	}


	public double getLongitud() {
		return longitud;
	}
    
}
