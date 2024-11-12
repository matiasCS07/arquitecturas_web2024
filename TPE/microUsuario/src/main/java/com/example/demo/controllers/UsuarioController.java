package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MonopatinResponseDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.dto.ViajeResponseDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;
import com.example.demo.services.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
	@Autowired
	UsuarioService userSer;
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> getAllUsers(){
		try {
			final var result=this.userSer.getAllUsers();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> getUserById(@PathVariable long id){
		try {			
			final var response=this.userSer.getUsuarioById(id);
			return ResponseEntity.ok(response);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable long id, @RequestBody @Valid Usuario user){
		try {
			final var result=this.userSer.updateUsuario(id, user);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable long id){
		try {
			this.userSer.cancelarCuenta(id);
			return ResponseEntity.noContent().build();
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("monopatin/activar/")
	public ResponseEntity<ViajeResponseDTO> activarMonopatin(@RequestBody @Valid double latitud, double longitud, long id){
		try {
			MonopatinResponseDTO monopatin=this.userSer.getMonopatinCercano(latitud, longitud);
			UsuarioResponseDTO usuario=this.userSer.getUsuarioById(id);
			ViajeResponseDTO response=this.userSer.crearViaje(monopatin, usuario);
			return ResponseEntity.ok(response);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/monopatin/cercanos")
	public ResponseEntity<List<MonopatinResponseDTO>> getMonopatinesCercanos(@RequestParam double latitud, @RequestParam double longitud){
		try {
			final var result=this.userSer.getAllMonopatinesCercanos(latitud, longitud);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("monopatin/ubicar/{idViaje}")
	public ResponseEntity<?> ubicarMonopatin(@RequestBody @Valid double latitud, double longitud, @PathVariable long idViaje){
		try {			
			final var result=this.userSer.terminarViaje(idViaje, latitud, longitud);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
