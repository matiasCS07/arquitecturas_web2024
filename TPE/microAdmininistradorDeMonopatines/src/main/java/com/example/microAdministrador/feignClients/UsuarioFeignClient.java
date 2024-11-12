package com.example.microAdministrador.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microAdministrador.dto.UsuarioResponseDTO;

@FeignClient(name = "microUsuario")
public interface UsuarioFeignClient {
	
	@GetMapping("/api/usuarios/{id}")
	ResponseEntity<UsuarioResponseDTO> getUserById(@PathVariable long id);
	
	@DeleteMapping("/api/usuarios/{id}")
	ResponseEntity<Void> deleteUsuario(@PathVariable long id);
}
