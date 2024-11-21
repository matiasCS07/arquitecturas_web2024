package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.TarifaResponseDTO;
import com.example.demo.entities.Tarifa;
import com.example.demo.repositories.TarifaRepository;

@Service
public class TarifaService {
	@Autowired
	private TarifaRepository tarifaRep;
	
	@Autowired
	private MongoTemplate mTemplate;
	
	@Transactional
	public TarifaResponseDTO establecerNuevaTarifa(Tarifa tarifa) {
		this.tarifaRep.save(tarifa);
		TarifaResponseDTO response=new TarifaResponseDTO();
		response.setFechaInicio(tarifa.getFechaInicio());
		response.setTarifa(tarifa.getPrecio());
		return response;
	}
	
	@Transactional
	public Tarifa getTarifa(LocalDate fecha) {
		return tarifaRep.getVigenteByFecha(fecha, Sort.by("fechaInicio").ascending());
	}
	
	@Transactional
	public Tarifa getTarifaById(Long id) {
		return tarifaRep.findById(id).orElse(null);
	}
	
	@Transactional
	public List<Tarifa> getAllTarifas() {
		return tarifaRep.findAll();
	}
	
	@Transactional
	public void editarTarifa(long id, int tarifa) {
		Query query = new Query(Criteria.where("id").is(id));
	    Update update = new Update().set("precio", tarifa);
	    mTemplate.updateFirst(query, update, Tarifa.class);
	}
}
