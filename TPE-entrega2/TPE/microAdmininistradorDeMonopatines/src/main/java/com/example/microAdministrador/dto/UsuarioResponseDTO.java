package com.example.microAdministrador.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
	private long id_user;
	
	public long getId() {
		return id_user;
	}
}
