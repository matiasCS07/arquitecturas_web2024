package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MonopatinResponseDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.dto.ViajeFinalizadoDTO;
import com.example.demo.dto.ViajeResponseDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.feignClients.AdministradorFeignClient;
import com.example.demo.feignClients.ViajeFeignClient;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.exceptions.NotFoundException;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository userRep;
	@Autowired
	AdministradorFeignClient adminFeign;
	@Autowired
	ViajeFeignClient viajeFeign;
	
	@Transactional
	public List<UsuarioResponseDTO> getAllUsers(){
		List<Usuario> usuarios=this.userRep.findAll();
		List<UsuarioResponseDTO> response=new ArrayList<>();
		for(Usuario u:usuarios) {
			response.add(this.mapToUsuarioResponseDTO(u));
		}
		return response;
	}
	
	@Transactional
	public UsuarioResponseDTO updateUsuario(long id, Usuario u) {
		Usuario user=this.userRep.findById(id).orElseThrow(() -> new NotFoundException("No se encontro el usuario de id ",id));
		user=this.userRep.update(user.getId(), user.getAlta(), user.getNombre(), user.getEmail(), user.getCelular(), user.getMercadoPago());
		return this.mapToUsuarioResponseDTO(user);
	}
	
	@Transactional
	public UsuarioResponseDTO cancelarCuenta(long id) {
		Usuario user=this.userRep.findById(id).orElseThrow(() -> new NotFoundException("No se encontro el usuario de id ",id));
		this.userRep.delete(user);
		return this.mapToUsuarioResponseDTO(user);
	}
	
	@Transactional
	public ViajeFinalizadoDTO terminarViaje(long idViaje, double latitud, double longitud) {	
		ViajeFinalizadoDTO viaje=this.viajeFeign.finalizarViaje(idViaje, latitud, longitud).getBody();
		return viaje;
	}
	
	@Transactional
	public ViajeResponseDTO crearViaje(MonopatinResponseDTO monopatin, UsuarioResponseDTO usuario) {
		ViajeResponseDTO response=new ViajeResponseDTO();
		ResponseEntity<ViajeResponseDTO> result=this.viajeFeign.createViaje(monopatin.getId(), usuario.getId());
		if(result.getStatusCode()==HttpStatus.BAD_REQUEST) {
			response.setExito(false);
			response.setMensaje("No se pudo crear el viaje");
			return response;
		}
		response=result.getBody();
		return response;
	}
	
	@Transactional
	public MonopatinResponseDTO getMonopatinCercano(double latitud, double longitud) {
		ResponseEntity<MonopatinResponseDTO> response=this.adminFeign.getMonopatinCercano(latitud, longitud);
		if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
			response.getBody().setExito(false);
			response.getBody().setMensaje("No se encontro el monopatin cercano");
			return response.getBody();
		}
		return response.getBody();
	}
	
	@Transactional
	public List<MonopatinResponseDTO> getAllMonopatinesCercanos(double latitud, double longitud) {
		return this.adminFeign.getAllMonopatinesCercanos(latitud, longitud).getBody();
	}
	
	@Transactional
	public UsuarioResponseDTO getUsuarioById(long id) {
		UsuarioResponseDTO response=new UsuarioResponseDTO();
		Usuario user=this.userRep.findById(id).orElse(null);
		if(user==null) {
			response.setMensaje("el usuario de id "+id+" no existe");
			response.setExito(false);
			return response;
		}
		response=mapToUsuarioResponseDTO(user);
		return response;
	}
	
	public UsuarioResponseDTO mapToUsuarioResponseDTO(Usuario user) {
		UsuarioResponseDTO dto=new UsuarioResponseDTO();
		dto.setCelular(user.getCelular());
		dto.setEmail(user.getEmail());
		dto.setNombre(user.getNombre());
		dto.setId(user.getId());
		return dto;
	}
}
