package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer>{
	@Query("SELECT c, COUNT(i.estudiante) as cantidadInscriptos FROM Carrera c JOIN c.inscripciones i GROUP BY c HAVING COUNT(i.estudiante) > 0 ORDER BY cantidadInscriptos DESC")
    List<Object[]> findCarrerasConInscriptos();
    
    @Query(value = "SELECT c.NOMBRE_CARRERA, i.ingreso, COUNT(CASE WHEN i.graduado = true THEN 1 END) AS egresados FROM CARRERA c LEFT JOIN INSCRIPCION i ON i.id_Carrera = c.id_Carrera GROUP BY c.NOMBRE_CARRERA, i.ingreso ORDER BY c. NOMBRE_CARRERA , i.ingreso ASC;", nativeQuery = true)
    List<Object[]> findReporteCarreras();
}
