package com.example.microAdministrador.feignClients;

import java.time.Duration;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import com.example.microAdministrador.dto.MonopatinRequestDTO;
import com.example.microAdministrador.dto.MonopatinResponseDTO;

@FeignClient(name = "microMonopatin")
public interface MonopatinFeignClient {
	
	@GetMapping("/api/monopatines/cercano")
	ResponseEntity<MonopatinResponseDTO> getMonopatinCercano(@RequestBody @Valid double latitud, double longitud);

	@GetMapping("/api/monopatines/cercanos")
	ResponseEntity<List<MonopatinResponseDTO>> getAllMonopatinesCercanos(@RequestBody @Valid double latitud, double longitud);
	
	@GetMapping("/api/monopatines/{id}")
	MonopatinResponseDTO getMonopatinById(@PathVariable long id);
	
	@PostMapping("/api/monopatines/añadir")
	ResponseEntity<MonopatinResponseDTO> addMonopatin(@RequestBody @Valid MonopatinRequestDTO mono);
	
	@PatchMapping("/api/monopatines/mantenimiento/{id}")
	ResponseEntity<MonopatinResponseDTO> cambiarMonopatinMantenimiento(@PathVariable long id, @RequestBody @Valid String estado, boolean habilitado);
	
	@PatchMapping("/api/monopatines/tiempo/{id}")
	ResponseEntity<MonopatinResponseDTO> actualizarTiempoMonopatin(@PathVariable long id, @RequestBody @Valid Duration tiempo);
	
	@GetMapping("/api/monopatines/habilitados/{habilitado}")
	ResponseEntity<List<Long>> getMonopatinesByHabilitado(@PathVariable boolean habilitado);
}
