package com.example.demo.services.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    @SuppressWarnings("unused")
	private final String message;

    public NotFoundException(String entity, Long id ){
        this.message = String.format( "La entidad %s con id %s no existe.", entity, id );
    }

	public NotFoundException(double latitud, double longitud) {
		this.message = String.format( "La parada con la longitud % y con latitud % no existe.", latitud, longitud);
	}
}
