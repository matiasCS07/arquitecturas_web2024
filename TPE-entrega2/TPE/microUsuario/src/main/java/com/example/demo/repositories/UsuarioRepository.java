package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.CuentaMP;
import com.example.demo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	@Query("UPDATE Usuario u "
			+ "SET "
			+ "u.fecha_alta = :alta "
			+ "u.nombre = :nombre "
			+ "u.email = :email "
			+ "u.celular = :celular "
			+ "u.cuenta_mp = :cuenta "
			+ "WHERE u.id_usuario = :id")
	public Usuario update(@Param("id") long id, @Param("alta") LocalDate localDate, @Param("nombre") String nombre, 
							@Param("email") String email, @Param("celular") String celular, @Param("cuanta") CuentaMP cuenta);
	
	Optional<Usuario> findByEmail(String email);
}
