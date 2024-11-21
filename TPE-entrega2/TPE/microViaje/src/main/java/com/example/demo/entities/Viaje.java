package com.example.demo.entities;


import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Viaje {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@JoinColumn(name="id_monopatin")
	private long idMonopatin;
	@JoinColumn(name="id_user")
	private long idUsuario;
	@Column
	private LocalDateTime inicio;
	@Column
	private LocalDateTime finalizacion;
	@Column(name="pausa_duracion")
	private Duration pausa;
	@Column(name="kilometros_recorridos")
	private int kilometrosRecorridos;
	
	public Viaje(long idUsuario, long idMonopatin) {
		this.idMonopatin=idMonopatin;
		this.idUsuario=idUsuario;
		this.inicio=LocalDateTime.now();
		this.kilometrosRecorridos=0;
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public Duration getPausa() {
		return pausa;
	}

	public int getKilometrosRecorridos() {
		return kilometrosRecorridos;
	}

	public LocalDateTime getFinalizacion() {
		return finalizacion;
	}

	public long getIdMonopatin() {
		return idMonopatin;
	}

	public long getIdUsuario() {
		return idUsuario;
	}
}
