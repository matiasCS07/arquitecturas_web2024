package com.example.microAdministrador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	private long id;
	
	public long getId() {
		return id;
	}
}
