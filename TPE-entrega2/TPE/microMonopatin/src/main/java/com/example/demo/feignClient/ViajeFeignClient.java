package com.example.demo.feignClient;

import java.time.Duration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microViaje")
public interface ViajeFeignClient {
	
	@GetMapping("api/viajes/pausa/{idMonopatin}")
	public ResponseEntity<Duration> getPausasById(@PathVariable long idMonopatin);
}
