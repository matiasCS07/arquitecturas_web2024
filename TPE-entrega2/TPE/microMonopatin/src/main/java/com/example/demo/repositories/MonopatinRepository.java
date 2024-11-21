package com.example.demo.repositories;

import com.example.demo.entities.Monopatin;

import java.time.Duration;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {
	@Query("SELECT m FROM Monopatin m "
			+ " WHERE m.estado = :estado AND m.hanilitado = :habilitado "
			+ "ORDER BY ((m.latitud - :latitud) * (m.latitud - :lattitud) + (m.longitud - :longitud) * (m.longitud - :longitud)) ASC")
	public Monopatin getMonopatinCercano(@Param("latitud")double latitud, @Param("longitud") double longitud, @Param("estado") String estado, @Param("habilitado") boolean habilitado,Pageable limite);

	@Query("SELECT m FROM Monopatin m "
			+ " WHERE m.estado = :estado AND m.hanilitado = :habilitado "
			+ "ORDER BY ((m.latitud - :latitud) * (m.latitud - :lattitud) + (m.longitud - :longitud) * (m.longitud - :longitud)) ASC")
	public Monopatin getAllMonopatinesCercanos(@Param("latitud")double latitud, @Param("longitud") double longitud, @Param("estado") String estado, @Param("habilitado") boolean habilitado);
	
	@Query("UPDATE Monopatin m SET m.estado = :estado, m.habilitado = :habilitado "
			+ "WHERE m.id_monopatin = :id")
	public void changeMonopatinOnMaintenance(@Param("id") long id, @Param("estado") String estado, @Param("habilitado") boolean habilitado);
	
	@Query("SELECT m FROM Monopatin m "
			+ "ORDER BY m.kilometros_totales DESC")
	public List<Monopatin> getAllMonopatinesByKilometro();
	
	@Query("UPDATE Monopatin m SET m.tiempo_uso = :tiempoUso WHERE m.id_monopatin = :id")
	public void actualizarTiempoMonopatin(@Param("id") long id, @Param("tiempoUso") Duration tiempoUso);
	
	@Query("SELECT m FROM Monopatin m "
			+ "ORDER BY m.tiempoUso DESC")
	public List<Monopatin> getAllMonopatinesByUsoConPausas();
	
	@Query("SELECT m.id_monopatin FROM Monopatin m "
			+ "WHERE m.habilitado = :habilitado ")
	public List<Long> getMonopatinesByHabilitado(@Param("habilitado") boolean habilitado);
}
