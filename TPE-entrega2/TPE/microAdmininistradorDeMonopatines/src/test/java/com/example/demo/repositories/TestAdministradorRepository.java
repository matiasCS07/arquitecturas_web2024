package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.microAdministrador.feignClients.TarifaFeignClient;
import com.example.microAdministrador.model.Tarifa;
import com.example.microAdministrador.repositories.AdministradorRepository;

@DataJpaTest
public class TestAdministradorRepository {
	@Mock
	private TarifaFeignClient tarifaFeign;
	@InjectMocks
	AdministradorRepository adminRep;
	
	//test sólo de métodos que realizan una acción unica, es decir
	//si para añadir o borrar una parada, utilizo updateParadas, este
	//lo testeo una vez
	
	@DisplayName("test sobre el guardado y obtencion de una tarifa")
	@Test
	void testGetTarifa() {
		Tarifa tarifa=new Tarifa();
		tarifa.setPrecio(200L);
		tarifa.setId(99L);
		tarifa.setFechaInicio(LocalDate.now());
		
		List<Tarifa>tarifas=new ArrayList<>();
		tarifas.add(any(Tarifa.class));
		tarifas.add(any(Tarifa.class));
		tarifas.add(any(Tarifa.class));
		tarifas.add(any(Tarifa.class));
		
		tarifas.add(tarifa);
		adminRep.updateTarifas(tarifas);
		List<Tarifa> tarifasGuardadas=adminRep.getAllTarifas();
		
		
		assertThat(tarifasGuardadas).isNotNull();
		assertThat(tarifasGuardadas).isNotEmpty();
		assertThat(tarifasGuardadas).contains(tarifa);
		assertThat(tarifasGuardadas.size()).isEqualTo(5);
	}
	
	@DisplayName("test para la obtencion de todos las ")
	@Test
	void testGetAllParadas() {
		Double[] parada1= {43.2,56.3};
		Double[] parada2= {54.2,21.4};
		List<Double[]> paradas=new ArrayList<>();
		paradas.add(parada2);
		paradas.add(parada1);
		
		Assert.assertNotEquals(parada1, parada2);
		adminRep.updateParadas(paradas);
		List<Double[]> paradasGuardadas=adminRep.getAllParadas();
		
		assertThat(paradasGuardadas).isNotNull();
		assertThat(paradasGuardadas.size()).isEqualTo(2);
		
		
	}
}
