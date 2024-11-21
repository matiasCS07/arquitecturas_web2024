package com.example.demo.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.MonopatinResponseDTO;

import jakarta.validation.Valid;

@FeignClient(name="microAdministradorDeMonopatines")
public interface AdministradorFeignClient {
	
	@GetMapping("/api/administrador/monopatin/cercano")
	ResponseEntity<MonopatinResponseDTO> getMonopatinCercano(@RequestBody @Valid double latitud, double longitud);

	@GetMapping("/api/administrador/monopatines/cercanos")
	ResponseEntity<List<MonopatinResponseDTO>> getAllMonopatinesCercanos(@RequestBody @Valid double latitud, double longitud);
}
