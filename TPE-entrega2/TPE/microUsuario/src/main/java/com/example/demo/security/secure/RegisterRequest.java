package com.example.demo.security.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	String nombre;
	String email;
	String password;
	String celular;
	double saldo;
}
