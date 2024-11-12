package com.example.microAdministrador.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microAdministrador.dto.AdministradorDTO;
import com.example.microAdministrador.dto.MonopatinReporteDTO;
import com.example.microAdministrador.dto.MonopatinRequestDTO;
import com.example.microAdministrador.dto.MonopatinResponseDTO;
import com.example.microAdministrador.dto.MonopatinesHabilitadosDTO;
import com.example.microAdministrador.dto.TarifaResponseDTO;
import com.example.microAdministrador.feignClients.UsuarioFeignClient;
import com.example.microAdministrador.feignClients.ViajeFeignClient;
import com.example.microAdministrador.model.Monopatin;
import com.example.microAdministrador.feignClients.MonopatinFeignClient;
import com.example.microAdministrador.repositories.AdministradorRepository;
import com.example.microAdministrador.dto.UsuarioResponseDTO;
import com.example.microAdministrador.entities.AdministradorDeMonopatines;
import com.example.microAdministrador.entities.Tarifa;
import com.example.microAdministrador.service.exception.NotFoundException;
import jakarta.validation.Valid;

@Service
public class AdministradorService {
	@Autowired
	AdministradorRepository adminRep;
	@Autowired
	MonopatinFeignClient monoFeign;
	@Autowired
	UsuarioFeignClient userFeign;
	@Autowired
	ViajeFeignClient viajeFeign;
	
	@Transactional
	public AdministradorDTO getAdministrador() {
		AdministradorDTO dto=new AdministradorDTO();
		AdministradorDeMonopatines admin=this.adminRep.findAll().get(0);
		dto.setId(admin.getId_admin());
		dto.setMonopatines(admin.getMonopatines());
		dto.setParadas(admin.getParadas());
		dto.setTarifa(admin.getTarifa());
		dto.setTarifaExtra(admin.getTarifaExtra());
		
		return dto;
	}
	
	@Transactional
	public TarifaResponseDTO establecerNuevaTarifa(long tarifa, int dia, int mes, int anio) {
		TarifaResponseDTO response=new TarifaResponseDTO();
		LocalDate fechaRequest=LocalDate.of(anio, mes, dia);
		Tarifa newTarifa=new Tarifa();
		newTarifa.setFechaInicio(fechaRequest);
		newTarifa.setPrecio(tarifa);
		response.setFechaInicio(newTarifa.getFechaInicio());
		response.setTarifa(newTarifa.getPrecio());
		this.adminRep.saveTarifa(newTarifa.getPrecio(), newTarifa.getFechaInicio());
		List<Tarifa> tarifas=this.adminRep.getAllTarifas();
		tarifas.add(newTarifa);
		this.adminRep.updateTarifas(tarifas);
		return response;
	}
	
	@Transactional
	public TarifaResponseDTO establecerNuevaTarifaExtra(long tarifa, int dia, int mes, int anio) {
		TarifaResponseDTO response=new TarifaResponseDTO();
		LocalDate fechaRequest=LocalDate.of(anio, mes, dia);
		Tarifa newTarifaExtra=new Tarifa();
		newTarifaExtra.setFechaInicio(fechaRequest);
		newTarifaExtra.setPrecio(tarifa);
		response.setFechaInicio(newTarifaExtra.getFechaInicio());
		response.setTarifa(newTarifaExtra.getPrecio());
		this.adminRep.saveTarifa(newTarifaExtra.getPrecio(), newTarifaExtra.getFechaInicio());
		List<Tarifa> tarifas=this.adminRep.getAllTarifas();
		tarifas.add(newTarifaExtra);
		this.adminRep.updateTarifas(tarifas);
		return response;
	}
	
	@Transactional
	public MonopatinResponseDTO getMonopatinCercano(double latitud, double longitud) {
		ResponseEntity<MonopatinResponseDTO> response=this.monoFeign.getMonopatinCercano(latitud, longitud);
		if(response.getStatusCode()!=HttpStatus.OK) {
			response=null;
		}
		return response.getBody();
	}
	
	@Transactional
	public List<MonopatinResponseDTO> getAllMonopatinesCercanos(double latitud, double longitud) {
		ResponseEntity<List<MonopatinResponseDTO>> response=this.monoFeign.getAllMonopatinesCercanos(latitud, longitud);
		if(response.getStatusCode()!=HttpStatus.OK) {
			response=null;
		}
		return response.getBody();
	}
	
	@Transactional
	public MonopatinesHabilitadosDTO getReporteEstadoMonopatin() {
		MonopatinesHabilitadosDTO reporte=new MonopatinesHabilitadosDTO();
		List<Long> idHabilitados=this.monoFeign.getMonopatinesByHabilitado(true).getBody();
		List<Long> idNoHabilitados=this.monoFeign.getMonopatinesByHabilitado(false).getBody();
		reporte.setCantidadHabilitados(idHabilitados.size());
		reporte.setCantidadNoHabilitados(idNoHabilitados.size());
		reporte.setMonopatinesHabilitados(idHabilitados);
		reporte.setMonopatinesNoHabilitados(idNoHabilitados);
		return reporte;
	}
	
	@Transactional
	public Long getFacturadoByFecha(int ini, int fin, int anio) {
		Long minutosViajes=this.viajeFeign.getMinutosViajesByTiempo(ini, fin, anio).getBody();
		Long tarifaTotal=(minutosViajes)*(this.adminRep.getTarifa(LocalDateTime.now()).orElse(null).getPrecio());
		return tarifaTotal;
	}
	
	@Transactional
	public List<MonopatinReporteDTO> getReporteViajeMonopatin(int cantViajes, int anio){
		List<Long> identificadores=this.viajeFeign.getMonopatinesByCantViajes(cantViajes, anio).getBody();
		List<MonopatinReporteDTO> monopatines=new ArrayList<>();
		for(Long id:identificadores) {
			monopatines.add(this.mapToCantidadDTO(this.monoFeign.getMonopatinById(id)));
		}
		return monopatines;
	}
	
	public MonopatinReporteDTO mapToCantidadDTO(MonopatinResponseDTO m) {
		MonopatinReporteDTO dto=new MonopatinReporteDTO();
		dto.setId_monopatin(m.getId());
		dto.setKilometrosTotales(m.getKilometrosTotales());
		dto.setLatitud(m.getKilometrosTotales());
		dto.setLongitud(m.getLongitud());
		dto.setTiempoUso(m.getTiempoUso());
		dto.setHabilitado(m.isHabilitado());
		dto.setEstado(m.getEstado());
		return dto;
	}
	
	@Transactional
	public MonopatinResponseDTO actualizarTiempoMonopatin(long id, Duration tiempo) {
		ResponseEntity<MonopatinResponseDTO> patin=this.monoFeign.actualizarTiempoMonopatin(id, tiempo);
		if(patin.getStatusCode()!=HttpStatusCode.valueOf(200)) {
			MonopatinResponseDTO response=new MonopatinResponseDTO();
			response.setExito(false);
			response.setMensaje("no se pudo actualizar el tiempo del patin");
		}
		return patin.getBody();
	}
	
	@Transactional
	public void cancelarCuenta(long id) {
		ResponseEntity<UsuarioResponseDTO> user=this.userFeign.getUserById(id);
		if(user==null) {			
			new RuntimeException("el user de id "+id+" no existe");
		}
		this.userFeign.deleteUsuario(user.getBody().getId());
	}
	
	@Transactional
	public MonopatinResponseDTO changeMonopatinOnMaintenance(long id, String estado, boolean habilitado) {
		ResponseEntity<MonopatinResponseDTO> mantenimientoResponse=this.monoFeign.cambiarMonopatinMantenimiento(id, estado, habilitado);
		if(mantenimientoResponse.getStatusCode()!=HttpStatusCode.valueOf(200)) {
			MonopatinResponseDTO response=new MonopatinResponseDTO();
			response.setExito(false);
			response.setMensaje("No se pudo poner en mantenimiento al monopatin de id "+id);
			return response;
		}
		return mantenimientoResponse.getBody();
	}
	
	@Transactional
	public List<Double[]> getAllParadas(){
		return this.adminRep.getAllParadas();
	}
	
	@Transactional
	public void borrarMonopatin(long id) {
		MonopatinResponseDTO m=this.monoFeign.getMonopatinById(id);
		if(m==null) {			
			new RuntimeException("el monopatin de id "+id+" no existe");
		}
		int i=0;
		boolean found=false;
		List<Monopatin> patines=adminRep.getAllMonopatines();
		while(i<patines.size()&&!found) {
			if(m.getId()==id) {
				found=true;
			}else {
				i++;
			}
		}
		
		if(found) {
			patines.remove(i);
			adminRep.updateMonopatines(patines);
		}
		
	}
	
	@Transactional
	public MonopatinResponseDTO añadirMonopatin(@Valid MonopatinRequestDTO mono) {
		ResponseEntity<MonopatinResponseDTO> response=this.monoFeign.addMonopatin(mono);
		MonopatinResponseDTO monopatin=this.monoFeign.getMonopatinById(response.getBody().getId());
		List<Monopatin> patines=adminRep.getAllMonopatines();
		patines.add(new Monopatin(monopatin.getId(), monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoUso(), monopatin.getEstado(), monopatin.getKilometrosTotales(), monopatin.isHabilitado()));
		this.adminRep.updateMonopatines(patines);
		return response.getBody();
	}
	
	@Transactional
	public List<Double[]> añadirParada(double latitud, double longitud) {
		List<Double[]> paradas=this.adminRep.getAllParadas();
		Double[] newParada={latitud, longitud};
		paradas.add(newParada);
		this.adminRep.updateParadas(paradas);
		return this.adminRep.getAllParadas();
	}
	
	@Transactional
	public void deleteParada(double latitud, double longitud) {
		List<Double[]> paradas=this.adminRep.getAllParadas();
		int i=this.getIndexParada(latitud, longitud);
		if(i!=-1) {
			paradas.remove(i);
			this.adminRep.updateParadas(paradas);
		}else {
			throw new NotFoundException(latitud, longitud);
		}
	}
	
	@Transactional
	public Double[] getParada(double latitud, double longitud) {
		List<Double[]> paradas=this.adminRep.getAllParadas();
		int i=this.getIndexParada(latitud, longitud);
		if(i!=-1) {
			return paradas.get(i);
		}else {
			throw new NotFoundException(latitud, longitud);
		}
	}
	
	@Transactional
	public void editarTarifa(int tarifa) {
		if(tarifa>0) {			
			this.adminRep.updateTarifa(tarifa);
		}
	}
	
	@Transactional
	public void editarTarifaExtra(int tarifaExtra) {
		if(tarifaExtra>0) {
			this.adminRep.updateTarifa(tarifaExtra);
		}
	}
	
	@Transactional
	public Tarifa getTarifa() {
		Tarifa tarifa=this.adminRep.getTarifa(LocalDateTime.now()).orElse(null);
		return tarifa; 
	}
	
	public int getIndexParada(double latitud, double longitud) {
		int i=0;
		boolean found=false;
		List<Double[]> paradas=this.adminRep.getAllParadas();
		while(i<paradas.size()&&!found) {
			Double[] parada=paradas.get(i);
			if(parada[0]==longitud&&parada[1]==latitud) {
				found=true;
			}else {
				i++;
			}
		}
		if(found) {			
			return i;
		}else {
			return -1;
		}
	}
	
	
}
