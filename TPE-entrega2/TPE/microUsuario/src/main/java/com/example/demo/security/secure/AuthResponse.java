package com.example.demo.security.secure;

import lombok.Data;

@Data
public class AuthResponse {
	String token;
	
	public AuthResponse(String t) {
		this.token=t;
	}
}