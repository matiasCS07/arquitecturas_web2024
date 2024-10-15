package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entidades.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
	List<Estudiante> findAll(Sort sort);
	List<Estudiante> findAllByGenero(String genero);
	Optional<Estudiante> findByLibretaUniversitaria(int libretaUniversitaria);
	@Query("SELECT e FROM Estudiante e JOIN e.inscripciones i WHERE i.carrera.idCarrera = :idCarrera AND e.ciudadResidencia = :ciudad")
    List<Estudiante> findByCarreraAndCiudad(@Param("idCarrera") Integer idCarrera, @Param("ciudad") String ciudad);
}
