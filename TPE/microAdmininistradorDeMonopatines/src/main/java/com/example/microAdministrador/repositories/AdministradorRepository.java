package com.example.microAdministrador.repositories;

import com.example.microAdministrador.entities.AdministradorDeMonopatines;
import com.example.microAdministrador.entities.Tarifa;
import com.example.microAdministrador.model.Monopatin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorDeMonopatines, Long> {
	@Query("UPDATE administrador_monopatines am SET am.tarifa = :tarifa")
	public void updateTarifa(@Param("tarifa") int tarifa);
	
	@Query("UPDATE administrador_monopatines am SET am.tarifaExtra = :tarifa")
	public void updateTarifaExtra(@Param("tarifa") int extra);
	
	@Query("UPDATE administrador_monopatines am SET am.paradas = :paradas")
	public void updateParadas(@Param("paradas") List<Double[]>paradas);
	
	@Query("UPDATE administrador_monopatin am SET am.monopatines = :patines")
	public void updateMonopatines(@Param("patines") List<Monopatin> patin);
	
	public List<Monopatin> getAllMonopatines();
	
	public List<Double[]> getAllParadas();
	
	@Query("UPDATE administrador_monopatines am SET am.tarifa = :tarifas")
	public void updateTarifas(@Param("tarifas") List<Tarifa> tarifas);
	
	@Query("SELECT t FROM tarifa t "
			+ "WHERE t.fecha_vigencia <= :fecha_actual "
			+ "ORDER BY t.fecha_vigencia DESC")
	public Optional<Tarifa> getTarifa(@Param("fecha_actual") LocalDateTime ahora);
	
	@Query("INSERT INTO tarifa t (t.precio, t.fecha_vigencia) "
			+ "VALUES (:precio, :fecha)")
	public void saveTarifa(@Param("precio") Long precio, @Param("fecha") LocalDate fecha);
	
	@Query("SELECT t FROM tarifa t")
	public List<Tarifa> getAllTarifas();
}
