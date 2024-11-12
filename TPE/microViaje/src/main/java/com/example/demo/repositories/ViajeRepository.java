package com.example.demo.repositories;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long>{
	@Query("UPDATE viaje v SET finalizacion = :finalizacion"
			+ "WHERE id = :id")
	public void finalizarViaje(@Param("id") long id, @Param("finalizacion") LocalDateTime fecha);
	
	@Query("SELECT SUM(v.pausa) FROM viaje "
			+ "WHERE id_monopatin = :id")
	public Duration getPausasByMonopatin(@Param("idMonopatin") long id);
	
	@Query("SELECT v.id_monopatin FROM viaje v "
			+ "WHERE YEAR(v.inicio) = :anio "
		     + "GROUP BY v.monopatin.id "
		     + "HAVING COUNT(v) > :cantViajes")
	public List<Long> getMonopatinesByCantViajes(@Param("cantViajes") int cantViajes, @Param("anio") int anio);
	
	@Query("SELECT SUM(TIMESTAMPDIFF(MINUTE, v.inicio, v.finalizacion)) "
			+ "FROM viaje v "
			+ "WHERE YEAR(v.inicio) = :anio "
			+ "AND MONTH(v.inicio) BETWEEN :mesIni AND :mesFin")
	public Long getMinutosViajesByTiempo(@Param("mesIni") int ini, @Param("mesFin") int fin, @Param("anio") int anio);
	
	@Query("UPDATE viaje v "
			+ "SET "
			+ "v.inicio = :inicio "
			+ "v.finalizacion = :fin "
			+ "v.pausa = :pausa "
			+ "v.kilometros_recorridos = :kilometros "
			+ "WHERE v.id = :id")
	public Viaje update(@Param("id") long id, @Param("inicio") LocalDateTime inicio, @Param("finalizacion") LocalDateTime fin, 
							@Param("pausa") Duration pausa, @Param("kilometros") int kilometros);
}
