package com.example.demo.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.ViajeFinalizadoDTO;
import com.example.demo.dto.ViajeResponseDTO;

import jakarta.validation.Valid;

@FeignClient(name="microViaje")
public interface ViajeFeignClient {
	
	@PostMapping("api/viajes/{idUsuario}/{idMonopatin}")
	public ResponseEntity<ViajeResponseDTO> createViaje(@PathVariable long idUsuario, @PathVariable long idMonopatin);
	
	@GetMapping("api/viajes/{idViaje}")
	public ResponseEntity<ViajeResponseDTO> getViajeById(@PathVariable long idViaje);
	
	@PostMapping("api/viajes/finalizar/{idViaje}")
	public ResponseEntity<ViajeFinalizadoDTO> finalizarViaje(@PathVariable long id, @RequestBody @Valid double latitud, double longitud);
}
