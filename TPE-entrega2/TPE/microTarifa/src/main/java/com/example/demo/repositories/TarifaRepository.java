package com.example.demo.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Tarifa;

@Repository
public interface TarifaRepository extends MongoRepository<Tarifa, Long>{
	@Query("{ 'fechaInicio' : { $gte: ?0 } }")
	public Tarifa getVigenteByFecha(LocalDate hoy, Sort sort);
}
