package com.example.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private long id;
	@Column(name="fecha_alta")
	private LocalDateTime alta;
	@Column
	private String nombre;
	@Column
	private String email;
	@Column
	private String celular;
	@Column(name="cuenta_mp")
	private CuentaMP mercadoPago;
	
	public long getId() {
		return id;
	}
	public LocalDateTime getAlta() {
		return alta;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	public String getCelular() {
		return celular;
	}
	public CuentaMP getMercadoPago() {
		return mercadoPago;
	}
}
