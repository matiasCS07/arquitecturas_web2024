package com.example.demo.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.CuentaMP;
import com.example.demo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	@Query("UPDATE usuario u "
			+ "SET "
			+ "u.fecha_alta = :alta "
			+ "u.nombre = :nombre "
			+ "u.email = :email "
			+ "u.celular = :celular "
			+ "u.cuenta_mp = :cuenta "
			+ "WHERE u.id_usuario = :id")
	public Usuario update(@Param("id") long id, @Param("alta") LocalDateTime alta, @Param("nombre") String nombre, 
							@Param("email") String email, @Param("celular") String celular, @Param("cuanta") CuentaMP cuenta);
}
