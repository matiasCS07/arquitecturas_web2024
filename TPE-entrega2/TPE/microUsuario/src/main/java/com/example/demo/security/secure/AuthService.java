package com.example.demo.security.secure;

import com.example.demo.entities.Usuario;
import com.example.demo.model.Role;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.security.jwt.JwtService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
	private UsuarioRepository userRep;
	private JwtService	jwtSer;
	private AuthenticationManager authManager;

	public AuthResponse login(LoginRequest request) {
		// TODO Auto-generated method stub
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.email, request.password));
		UserDetails user=userRep.findByEmail(request.email).orElseThrow();
		String token=jwtSer.getToken(user);
		return new AuthResponse(token);
	}

	public AuthResponse register(RegisterRequest request) {
		Usuario user=new Usuario(request.email, request.nombre, request.password, request.celular, Role.USER, LocalDate.now(), request.saldo);
		
		userRep.save(user);
		
		return new AuthResponse(jwtSer.getToken(user));
	}

}
