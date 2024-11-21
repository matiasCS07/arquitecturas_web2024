package com.example.microAdministrador.repositories;

import com.example.microAdministrador.entities.AdministradorDeMonopatines;
import com.example.microAdministrador.model.Tarifa;
import com.example.microAdministrador.model.Monopatin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorDeMonopatines, Long> {
	
	@Query("UPDATE Administrador_monopatines am SET am.tarifaExtra = :tarifa")
	public void updateTarifaExtra(@Param("tarifa") List<Tarifa> tarifa);
	
	@Query("UPDATE Administrador_monopatines am SET am.paradas = :paradas")
	public void updateParadas(@Param("paradas") List<Double[]>paradas);
	
	@Query("UPDATE Administrador_monopatines am SET am.monopatines = :patines")
	public void updateMonopatines(@Param("patines") List<Monopatin> patin);
	
	public List<Monopatin> getAllMonopatines();
	
	public List<Double[]> getAllParadas();
	
	@Query("UPDATE Administrador_monopatines am SET am.tarifa = :tarifas")
	public void updateTarifas(@Param("tarifas") List<Tarifa> tarifas);
	
	@Query("SELECT am.tarifa FROM Administrador_monopatines am")
	public List<Tarifa> getAllTarifas();
	
	@Query("SELECT am.tarifaExtra FROM Administrador_monopatines am")
	public List<Tarifa> getAllTarifasExtra();
}
