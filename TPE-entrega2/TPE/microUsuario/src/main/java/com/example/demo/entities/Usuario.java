package com.example.demo.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="email", uniqueConstraints= {@UniqueConstraint(columnNames= {"email"})})
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private long id;
	@Column(name="fecha_alta")
	private LocalDate alta;
	@Column
	private String nombre;
	@Column(nullable=false)
	private String email;
	@Column
	private String password;
	@Column
	private Role rol;
	@Column
	private String celular;
	@Column(name="cuenta_mp")
	private CuentaMP mercadoPago;
	
	public Usuario(String mail, String nombre, String pass, String celu, Role user, LocalDate fecha, double saldo) {
		this.email=mail;
		this.nombre=nombre;
		this.password=pass;
		this.celular=celu;
		this.rol=user;
		this.alta=fecha;
		this.mercadoPago=new CuentaMP(saldo);
	}
	public long getId() {
		return id;
	}
	public LocalDate getAlta() {
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority((rol.name())));
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return email;
	}
}
