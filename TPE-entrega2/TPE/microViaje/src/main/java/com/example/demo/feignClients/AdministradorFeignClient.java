package com.example.demo.feignClients;

import java.time.Duration;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@FeignClient(name = "microAdministradorDeMonopatines")
public interface AdministradorFeignClient {
	
	@GetMapping("api/administrador/tarifa") 
	public ResponseEntity<Long> getTarifa();
	
	@GetMapping("api/administrador/paradas")
	public ResponseEntity<List<Double[]>> getAllParadas();
	
	@PatchMapping("api/administrador/monopatin/tiempo/{id}")
	public ResponseEntity<?> actualizarTiempoMonopatin(@PathVariable long id, @RequestBody @Valid Duration tiempo);
}
