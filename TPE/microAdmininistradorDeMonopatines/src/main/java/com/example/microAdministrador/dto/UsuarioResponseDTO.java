package com.example.microAdministrador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
	private long id_user;
	
	public long getId() {
		return id_user;
	}
}
