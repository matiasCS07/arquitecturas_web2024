package com.example.microAdministrador.feignClients;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.microAdministrador.model.Tarifa;


@FeignClient(name = "microTarifa")
public interface TarifaFeignClient {
	
	@GetMapping("api/tarifa/")
	public ResponseEntity<List<Tarifa>> getAllTarifas();
	
	@GetMapping("api/tarifa/")
	public ResponseEntity<Tarifa> getTarifa(@RequestParam LocalDate fecha);
	
	@GetMapping("api/tarifa/{id}")
	public ResponseEntity<Tarifa> getTarifaById(@PathVariable Long id);
	
	@PostMapping("api/tarifa/")
	public ResponseEntity<?> nuevaTarifa(@RequestBody Tarifa tarifa);
	
	@PatchMapping("api/tarifa/{id}")
	public ResponseEntity<?> editTarifa(@PathVariable long id, @RequestParam int tarifa);
}
