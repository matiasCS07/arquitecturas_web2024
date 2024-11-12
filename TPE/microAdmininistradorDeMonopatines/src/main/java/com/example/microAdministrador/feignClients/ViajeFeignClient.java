package com.example.microAdministrador.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microViaje")
public interface ViajeFeignClient {
	
	@GetMapping("api/viajes/buscar/cantidad/minimo/{cantViajes}")
	public ResponseEntity<List<Long>> getMonopatinesByCantViajes(@PathVariable int cantViajes, @PathVariable int anio);
	
	@GetMapping("api/viajes/buscar/minutos/fecha")
	public ResponseEntity<Long> getMinutosViajesByTiempo(@RequestParam int mesIni, @RequestParam int mesFin, @RequestParam int anio);
}
