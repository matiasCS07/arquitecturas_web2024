package com.example.demo.controllers;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.demo.dto.ViajeResponseDTO;
import com.example.demo.entities.Viaje;
import com.example.demo.services.ViajeService;
import com.example.demo.services.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/viajes")
public class ViajesController {
	@Autowired
	ViajeService viajeSer;
	
	@GetMapping("/pausa/{id}")
	public ResponseEntity<Duration> getPausasById(@PathVariable long idMonopatin){
		try {
			Duration result=this.viajeSer.getPausasById(idMonopatin);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/buscar/cantidadByTiempo")
	public ResponseEntity<Long> getMinutosViajesByTiempo(@RequestParam int mesIni, @RequestParam int mesFin, @RequestParam int anio){
		try {
			final var result=this.viajeSer.getMinutosViajesByTiempo(mesIni, mesFin, anio);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/buscar/cantidadByMinimo/{cantViajes}")
	public ResponseEntity<List<Long>> getMonopatinesByCantViajes(@PathVariable int cantViajes, @PathVariable int anio){
		try {
			final var result=this.viajeSer.getMonopatinesByCantViajes(cantViajes, anio);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createViaje(@RequestBody @Valid long idUsuario, long idMonopatin){
		try {
			final var result=this.viajeSer.addViaje(idUsuario, idMonopatin);
			return ResponseEntity.accepted().body(result);
		}catch(BadRequest e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> updateViaje(@PathVariable long id, @RequestBody Viaje v){
		try {
			final var result=this.viajeSer.updateViaje(id, v);
			return ResponseEntity.accepted().body(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<ViajeResponseDTO> deleteViaje(@PathVariable long id){
		try {
			final var result=this.viajeSer.deleteViaje(id);
			return ResponseEntity.accepted().body(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("/finalizar/{idViaje}")
	public ResponseEntity<?> finalizarViaje(@PathVariable long id, @RequestBody @Valid double latitud, double longitud){
		try {
			boolean estaEnParada=this.viajeSer.usuarioEstaEnParada(latitud, longitud);
			final var result=this.viajeSer.finalizarViaje(id, estaEnParada);
			return ResponseEntity.accepted().body(result);
		}catch(BadRequest | NotFoundException e) {
			if(e instanceof NotFoundException) {
				return ResponseEntity.notFound().build();
			}else {
				return ResponseEntity.badRequest().build();
			}
		}
	}
	
	@GetMapping("/{idViaje}")
	public ResponseEntity<ViajeResponseDTO> getViajeById(@PathVariable long id){
		try {
			ViajeResponseDTO result=this.viajeSer.getViajeById(id);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
