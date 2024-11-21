package com.example.demo.services;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.example.microAdministrador.dto.TarifaResponseDTO;
import com.example.microAdministrador.model.Tarifa;
import com.example.microAdministrador.feignClients.TarifaFeignClient;
import com.example.microAdministrador.feignClients.ViajeFeignClient;
import com.example.microAdministrador.repositories.AdministradorRepository;
import com.example.microAdministrador.service.AdministradorService;

@ExtendWith(MockitoExtension.class)
public class TestAdministradorService {
	
	@Mock
	private ViajeFeignClient viajeFeign;
	@Mock
	private AdministradorRepository adminRep;
	@Mock
	private TarifaFeignClient tarifaFeign;
	
	@InjectMocks
	private AdministradorService adminSer;
	
//	@BeforeEach 
//	public void setup() {
//	}
	
	@DisplayName("test para crear una nueva tarifa")
	@Test
	public void testEstablecerNuevaTarifa() {
		
		long tarifa=200;
		int dia=12, mes=02, anio=2024;
		TarifaResponseDTO response=new TarifaResponseDTO();
		response.setTarifa(tarifa);
		LocalDate fecha=LocalDate.parse(""+anio+"-"+mes+"-"+dia);
		response.setFechaInicio(fecha);
		
		Assert.assertEquals(adminSer.establecerNuevaTarifa(tarifa, dia, mes, anio), response);
	}
	
	
	//se utiliza mockito para hacer pruebas entre componentes
	//y microservicios cruzados
	
	@DisplayName("test para obtener lo facturado en una fecha")
	@Test
	public void testAñadirMonopatin() {
		ResponseEntity<Long> minutos=new ResponseEntity<Long>(534L, HttpStatusCode.valueOf(200));
		Tarifa t=new Tarifa();
		t.setFechaInicio(LocalDate.now());
		t.setId(54L);
		t.setPrecio(200L);
		
		given(viajeFeign.getMinutosViajesByTiempo(8, 12, 2024)).willReturn(minutos);
		given(tarifaFeign.getTarifa(LocalDate.now())).willReturn(ResponseEntity.ok(t));
		
		Long facturadoTotal=adminSer.getFacturadoByFecha(8, 12, 2024);
		
		assertThat(facturadoTotal).isNotNull();
		assertThat(facturadoTotal).isNotNegative();
		
		//el facturado total deberia ser 200*534=106800
		Assertions.assertEquals(facturadoTotal, 106800L);
	}
}
