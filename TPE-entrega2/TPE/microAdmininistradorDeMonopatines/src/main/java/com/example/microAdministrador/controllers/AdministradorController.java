package com.example.microAdministrador.controllers;

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

import com.example.microAdministrador.dto.AdministradorDTO;
import com.example.microAdministrador.dto.MonopatinReporteDTO;
import com.example.microAdministrador.dto.MonopatinRequestDTO;
import com.example.microAdministrador.dto.MonopatinResponseDTO;
import com.example.microAdministrador.dto.MonopatinesHabilitadosDTO;
import com.example.microAdministrador.model.Tarifa;
import com.example.microAdministrador.service.AdministradorService;
import com.example.microAdministrador.service.exception.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="api/administrador")
public class AdministradorController {
	@Autowired
	private AdministradorService adminSer;
	
	@GetMapping
	public ResponseEntity<AdministradorDTO> getAdministrador(){
		try {
			final var result=this.adminSer.getAdministrador();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//@Postmapping (no deberia existir otro administrador de monopatines)
	//@DeleteMapping (no se deberia poder borrar el administrador de monopatines)
	
	@GetMapping("/reportes/monopatines/cantidadViajes/{cantViajes}/{anio}")
	public ResponseEntity<List<MonopatinReporteDTO>> getReporteViajesMonopatin(@PathVariable int cantViajes, @PathVariable int anio){
		try {
			final var result=this.adminSer.getReporteViajeMonopatin(cantViajes, anio);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/reportes/monopatines/estado")
	public ResponseEntity<MonopatinesHabilitadosDTO> getReporteEstadoMonopatin(){
		try {
			final var result=this.adminSer.getReporteEstadoMonopatin();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/reportes/viajes/facturado/fecha")
	public ResponseEntity<Long> getFacturadoByFecha(@RequestParam int mesIni, @RequestParam int mesFin, @RequestParam int anio){
		try {
			final var result=this.adminSer.getFacturadoByFecha(mesIni, mesFin, anio); 
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<Void> deleteusuario(@PathVariable long id){
		try {
			this.adminSer.cancelarCuenta(id);
			return ResponseEntity.noContent().build();
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/paradas")
	public ResponseEntity<List<Double[]>> getAllParadas(){
		try {
			final var result=this.adminSer.getAllParadas();
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/parada/añadir")
	public ResponseEntity<?> añadirParada(@RequestBody double latitud, double longitud){
		try {
			final var result=this.adminSer.añadirParada(latitud, longitud);
			return ResponseEntity.ok(result);
		}catch(BadRequest e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("parada/borrar")
	public ResponseEntity<Void> deleteParada(@RequestParam double latitud, @RequestParam double longitud){
		try {
			this.adminSer.getParada(latitud, longitud);
			this.adminSer.deleteParada(latitud, longitud);
			return ResponseEntity.noContent().build();
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/tarifa")
	public ResponseEntity<Tarifa> getTarifa() {
		Tarifa tarifa=this.adminSer.getTarifaVigente();
		if(tarifa==null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(tarifa);
	}
	
		@PatchMapping("/tarifa/cambiar/")
	public ResponseEntity<?> establecerNuevaTarifa(@RequestParam("tarifa") Long precio, @RequestParam int dia, @RequestParam int mes, @RequestParam int anio){
		try {
			final var result=this.adminSer.establecerNuevaTarifa(precio, dia, mes, anio);
			return ResponseEntity.ok(result);
		}catch(BadRequest e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("/tarifa/extra/cambiar")
	public ResponseEntity<?> establecerNuevaTarifaExtra(@RequestParam("tarifa") Long precio, @RequestParam int dia, @RequestParam int mes, @RequestParam int anio){
		try {
			final var result=this.adminSer.establecerNuevaTarifaExtra(precio, dia, mes, anio);
			return ResponseEntity.ok(result);
		}catch(BadRequest e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("/tarifa")
	public ResponseEntity<?> editTarifa(@RequestParam int tarifa){
		try {
			this.adminSer.editarTarifa(tarifa);
			String mensaje="se ha cambiado la tarifa a "+tarifa+" de manera satisfactoria.";
			return ResponseEntity.ok(mensaje);
		}catch(BadRequest e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/tarifa/extra")
	public ResponseEntity<?> editTarifaExtra(@RequestParam int tarifa){
		try {
			this.adminSer.editarTarifaExtra(tarifa);
			String mensaje="se ha cambiado la tarifa extra a "+tarifa+" de manera satisfactoria.";
			return ResponseEntity.ok(mensaje);
		}catch(BadRequest e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/monopatines/cercanos")
	public ResponseEntity<List<MonopatinResponseDTO>> getAllMonopatinesCercanos(@RequestParam @Valid double latitud, @RequestParam @Valid double longitud){
		try {
			final var result=this.adminSer.getAllMonopatinesCercanos(latitud, longitud);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/monopatin/cercano")
	public ResponseEntity<MonopatinResponseDTO> getMonopatinCercano(@RequestParam @Valid double latitud, @RequestParam @Valid double longitud){
		try {
			final MonopatinResponseDTO result=this.adminSer.getMonopatinCercano(latitud, longitud);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/monopatin/añadir")
	public ResponseEntity<?> addMonopatin(@RequestBody @Valid MonopatinRequestDTO mono){
		final var result=this.adminSer.añadirMonopatin(mono);
		return ResponseEntity.accepted().body(result);
	}
	
	@PatchMapping("/monopatin/mantenimiento/{id}")
	public ResponseEntity<?> cambiarMonopatinEnMantenimiento(@PathVariable long id, @RequestParam @Valid String estado, @RequestParam @Valid boolean habilitado){
		try {
			final var result=this.adminSer.changeMonopatinOnMaintenance(id, estado, habilitado);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("monopatin/tiempo/{id}")
	public ResponseEntity<?> actualizarTiempoMonopatin(@PathVariable long id, @RequestParam @Valid Duration tiempo){
		try {
			final var result=this.adminSer.actualizarTiempoMonopatin(id, tiempo);
			return ResponseEntity.ok(result);
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/monopatin/{id}")
	public ResponseEntity<Void> deleteMonopatin(@PathVariable long id){
		try {
			this.adminSer.borrarMonopatin(id);
			return ResponseEntity.noContent().build();
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
