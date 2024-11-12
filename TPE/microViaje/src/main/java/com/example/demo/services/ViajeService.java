package com.example.demo.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.ViajeCantidadDTO;
import com.example.demo.dto.ViajeFinalizadoDTO;
import com.example.demo.dto.ViajeResponseDTO;
import com.example.demo.entities.Viaje;
import com.example.demo.feignClients.AdministradorFeignClient;
import com.example.demo.repositories.ViajeRepository;
import com.example.demo.services.exceptions.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ViajeService {
	@Autowired
	ViajeRepository viajeRep;
	@Autowired
	AdministradorFeignClient adminFeign;
	
	@Transactional
	public ViajeResponseDTO deleteViaje(long id) {
		Viaje viaje=this.viajeRep.findById(id).orElseThrow(() -> new NotFoundException("no se encontro viaje de id ",id));
		this.viajeRep.delete(viaje);
		return this.mapToViajeResponseDTO(viaje);
	}
	
	@Transactional
	public ViajeResponseDTO updateViaje(long id, Viaje v) {
		Viaje viaje=this.viajeRep.findById(id).orElseThrow(() -> new NotFoundException("no se encontro viaje de id ",id));
		viaje=this.viajeRep.update(viaje.getId(), viaje.getInicio(), viaje.getFinalizacion(), v.getPausa(), v.getKilometrosRecorridos());
		return this.mapToViajeResponseDTO(viaje);
	}
	
	@Transactional
	public ViajeResponseDTO addViaje(long idUsuario, long idMonopatin) {
		ViajeResponseDTO response=new ViajeResponseDTO();
		Viaje viaje=new Viaje(idUsuario, idMonopatin);
		this.viajeRep.save(viaje);
		response=mapToViajeResponseDTO(viaje);
		return response;
	}
	
	@Transactional
	public List<Long> getMonopatinesByCantViajes(int cantViajes, int anio){
		List<Long> identificadores=this.viajeRep.getMonopatinesByCantViajes(cantViajes, anio);
		return identificadores;
	}
	
	public ViajeCantidadDTO mapToViajeCantidadDTO(Viaje v) {
		ViajeCantidadDTO dto=new ViajeCantidadDTO();
		dto.setIdMonopatin(v.getIdMonopatin());
		dto.setCant_viajes(0);
		return dto;
	}
	
	@Transactional
	public Duration getPausasById(long idMonopatin) {
		Duration pausas=this.viajeRep.getPausasByMonopatin(idMonopatin);
		return pausas;
	}
	
	@Transactional
	public boolean usuarioEstaEnParada(double latitud, double longitud) { //las coordenadas del parametro son las del user
		List<Double[]> paradas=this.adminFeign.getAllParadas().getBody(); 
		int i=0;
		boolean estaEnParada=false;
		while(i<paradas.size()&&!estaEnParada) {
			Double[] parada=paradas.get(i);
			if(parada[0]==latitud&&parada[1]==longitud) {
				estaEnParada=true;
			}else {
				i++;
			}
		}
		return estaEnParada;
	}
	
	@Transactional
	public ViajeResponseDTO getViajeById(long idViaje) {
		ViajeResponseDTO response=new ViajeResponseDTO();
		Viaje viaje=this.viajeRep.findById(idViaje).orElse(null);
		if(viaje==null) {
			response.setExito(false);
			response.setMensaje("el viaje de id "+idViaje+" no existe");
			return response;
		}
		response=this.mapToViajeResponseDTO(viaje);
		return response;
	}
	
	@Transactional
	public Long getMinutosViajesByTiempo(int mesIni, int mesFin, int anio) {
		Long minutosViajes=this.viajeRep.getMinutosViajesByTiempo(mesIni, mesFin, anio);
		if(minutosViajes<0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo conseguir los minutos de viajes en esas fechas");
		}
		return minutosViajes;
	}
	
	@Transactional
	public ViajeFinalizadoDTO finalizarViaje(long id, boolean enParada) {
		ViajeFinalizadoDTO response=new ViajeFinalizadoDTO();
		Viaje viaje=this.viajeRep.findById(id).orElse(null);
		if(!enParada) {
			response.setExito(false);
			response.setMensaje("no puedes dejar el monopatin fuera de una parada");
		}else if(viaje==null) {
			response.setExito(false);
			response.setMensaje("no existe el viaje de id "+id);
			return response;
		}
		this.viajeRep.finalizarViaje(id, LocalDateTime.now());
		Duration tiempo=Duration.between(viaje.getInicio(), viaje.getFinalizacion());
		this.adminFeign.actualizarTiempoMonopatin(viaje.getIdMonopatin(), tiempo);
		response=this.mapToViajeFinalizadoDTO(viaje);
		return response;
	}
	
	public ViajeFinalizadoDTO mapToViajeFinalizadoDTO(Viaje v) {
		ViajeFinalizadoDTO dto=new ViajeFinalizadoDTO();
		Duration tiempoDeViaje=Duration.between(v.getInicio(), v.getFinalizacion());
		Long tarifa=this.adminFeign.getTarifa().getBody();
		dto.setCosto((tarifa)*(tiempoDeViaje.toMinutes()));
		return dto;
	}
	
	public ViajeResponseDTO mapToViajeResponseDTO(Viaje viaje) {
		ViajeResponseDTO dto=new ViajeResponseDTO();
		dto.setIdViaje(viaje.getId());
		dto.setIdMonopatin(viaje.getIdMonopatin());
		dto.setIdUsuario(viaje.getIdUsuario());
		dto.setInicio(viaje.getInicio());
		dto.setFinalizcion(viaje.getFinalizacion());
		return dto;
		
	}
}
