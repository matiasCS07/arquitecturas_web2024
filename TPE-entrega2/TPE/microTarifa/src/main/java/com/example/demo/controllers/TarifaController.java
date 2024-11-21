package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.demo.entities.Tarifa;
import com.example.demo.services.TarifaService;
import com.example.demo.services.exceptions.NotFoundException;

@RestController
@RequestMapping(value="api/tarifa")
public class TarifaController {
	
	@Autowired
	TarifaService tarifaSer;
	
	@GetMapping
	public ResponseEntity<Tarifa> getTarifaVigente() {
		Tarifa tarifa=this.tarifaSer.getTarifa(LocalDate.now());
		if(tarifa==null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(tarifa);
	}
	
	@GetMapping("api/tarifa/{id}")
	public ResponseEntity<Tarifa> getTarifaById(@PathVariable Long id){
		try {
			final Tarifa result=this.tarifaSer.getTarifaById(id);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Tarifa> getTarifa(@RequestParam LocalDate fecha) {
		Tarifa tarifa=this.tarifaSer.getTarifa(fecha);
		if(tarifa==null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(tarifa);
	}
	
	@GetMapping
	public ResponseEntity<List<Tarifa>> getAllTarifas() {
		List<Tarifa> tarifas=this.tarifaSer.getAllTarifas();
		if(tarifas.size()==0) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(tarifas);
	}
	
	@PostMapping
	public ResponseEntity<?> nuevaTarifa(@RequestBody Tarifa tarifa){
		try {
			final var result=this.tarifaSer.establecerNuevaTarifa(tarifa);
			return ResponseEntity.ok(result);
		}catch(BadRequest e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> editTarifa(@PathVariable long id, @RequestParam int tarifa){
		try {
			this.tarifaSer.editarTarifa(id, tarifa);
			String mensaje="se ha cambiado la tarifa a "+tarifa+" de manera satisfactoria.";
			return ResponseEntity.ok(mensaje);
		}catch(BadRequest e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
