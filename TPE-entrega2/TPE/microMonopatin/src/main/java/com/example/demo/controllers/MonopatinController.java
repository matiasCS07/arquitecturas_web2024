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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.demo.dto.MonopatinReporteDTO;
import com.example.demo.dto.MonopatinRequestDTO;
import com.example.demo.dto.MonopatinResponseDTO;
import com.example.demo.services.MonopatinService;
import com.example.demo.services.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/monopatines")
public class MonopatinController {
	@Autowired
	private MonopatinService monoSer;
	
	@GetMapping
	public ResponseEntity<List<MonopatinResponseDTO>> getAllMonopatines(){
		try {
			final var result=this.monoSer.getAllMonopatines();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<MonopatinResponseDTO> deleteMonopatin(@PathVariable long id){
		try {
			final var result=this.monoSer.deleteMonopatin(id);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/habilitados/{habilitado}")
	public ResponseEntity<List<Long>> getMonopatinesByHabilitado(@PathVariable boolean habilitado){
		try {
			final var result=this.monoSer.getMonopatinesByHabilitado(habilitado);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/reporte/usoKilometros/{conPausa}")
	public ResponseEntity<List<MonopatinReporteDTO>> getReporteByUso(@PathVariable boolean conPausa){
		try {
			final var result=this.monoSer.getReporteByUsoKilo(conPausa);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/reporte/kilometro")
	public ResponseEntity<List<MonopatinReporteDTO>> getReportebyKilometros(){
		try {
			final var result=this.monoSer.getReporteByKilometros();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/reporte/tiempoUso/conPausas")
	public ResponseEntity<List<MonopatinReporteDTO>> getReporteByUsoConPausas(){
		try {
			final var result=this.monoSer.getReporteByUsoConPausas();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/reporte/tiempoUso/sinPausas")
	public ResponseEntity<List<MonopatinReporteDTO>> getReporteByUsoSinPausas(){
		try {
			final var result=this.monoSer.getReporteByUsoSinPausas();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("/tiempo/{id}")
	public ResponseEntity<?> actualizarTiempoMonopatin(@PathVariable long id, @RequestParam @Valid Duration tiempo){
		try {
			final var result=this.monoSer.actualizarTiempoMonopatin(id, tiempo);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/añadir")
	public ResponseEntity<MonopatinResponseDTO> addMonopatin(@RequestBody @Valid MonopatinRequestDTO request){
		try {			
			final var result=this.monoSer.añadirMonopatin(request);
			return ResponseEntity.accepted().body(result);
		}catch(BadRequest e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/mantenimiento/{id}")
	public ResponseEntity<MonopatinResponseDTO> cambiarMonopatinMantenimiento(@PathVariable long id, @RequestParam @Valid String estado, @RequestParam @Valid boolean habilitado){
		try {
			final var result=this.monoSer.cambiarEstadoMonopatinEnMantenimiento(id, estado, habilitado);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MonopatinResponseDTO> getMonopatinById(@PathVariable long id) {
		try {			
			final var response=this.monoSer.getMonopatinById(id);
			return ResponseEntity.ok(response);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/cercano")
	public ResponseEntity<MonopatinResponseDTO> getMonopatinCercano(@RequestParam @Valid double latitud, @RequestParam @Valid double longitud){
		try {
			final var response=this.monoSer.getMonopatinCercano(latitud, longitud);
			return ResponseEntity.ok(response);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/cercanos")
	public ResponseEntity<MonopatinResponseDTO> getAllMonopatinesCercanos(@RequestParam @Valid double latitud, @RequestParam @Valid double longitud){
		try {
			final var response=this.monoSer.getAllMonopatinesCercanos(latitud, longitud);
			return ResponseEntity.ok(response);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
}
