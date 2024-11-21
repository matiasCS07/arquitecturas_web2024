package com.example.microAdministrador.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.microAdministrador.model.Monopatin;
import com.example.microAdministrador.model.Tarifa;

@Entity(name="administrador_monopatines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorDeMonopatines {
	
	@Id
	private long id_admin;
	
	@OneToMany
	private List<Monopatin> monopatines;
	
	@Column
	private List<Double[]> paradas;
	
	@OneToMany
	private List<Tarifa> tarifa; //al tener distintas tarifas, con distintos precios y fechas de vigencia. se guarda una lista de tarifas
	
	@OneToMany
	private List<Tarifa> tarifaExtra;

	public long getId_admin() {
		return id_admin;
	}

	public List<Monopatin> getMonopatines() {
		return monopatines;
	}

	public List<Double[]> getParadas() {
		return paradas;
	}

	public List<Tarifa> getTarifa() {
		return tarifa;
	}

	public List<Tarifa> getTarifaExtra() {
		return tarifaExtra;
	}
	
}
