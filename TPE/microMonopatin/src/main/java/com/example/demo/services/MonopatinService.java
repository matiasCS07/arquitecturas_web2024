package com.example.demo.services;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MonopatinReporteDTO;
import com.example.demo.dto.MonopatinRequestDTO;
import com.example.demo.dto.MonopatinResponseDTO;
import com.example.demo.entities.Monopatin;
import com.example.demo.feignClient.ViajeFeignClient;
import com.example.demo.repositories.MonopatinRepository;
import com.example.demo.services.exceptions.NotFoundException;


@Service
public class MonopatinService {
	@Autowired
	MonopatinRepository monoRep;
	@Autowired
	ViajeFeignClient viajeFeign;
	
	@Transactional
	public List<MonopatinResponseDTO> getAllMonopatines(){
		List<Monopatin> monopatines=this.monoRep.findAll();
		List<MonopatinResponseDTO> response=new ArrayList<>();
		for(Monopatin m:monopatines) {
			response.add(this.mapToMonopatinResponseDTO(m));
		}
		return response;
	}
	
	@Transactional
	public MonopatinResponseDTO deleteMonopatin(long id) {
		Monopatin monopatinAEliminar=this.monoRep.findById(id).orElseThrow(() -> new NotFoundException("no se encontro el monopatin de id ", id));
		this.monoRep.deleteById(id);
		return this.mapToMonopatinResponseDTO(monopatinAEliminar);
	}
	
	@Transactional
	public MonopatinResponseDTO actualizarTiempoMonopatin(long id, Duration tiempo) {
		this.monoRep.actualizarTiempoMonopatin(id, tiempo);
		Monopatin patin=this.monoRep.findById(id).orElse(null);
		return this.mapToMonopatinResponseDTO(patin);
		
	}
	
	@Transactional
	public List<Long> getMonopatinesByHabilitado(boolean habilitado){
		List<Long> identificadores=new ArrayList<>();
		identificadores=this.monoRep.getMonopatinesByHabilitado(habilitado);
		return identificadores;
	}
	
	@Transactional
	public List<MonopatinReporteDTO> getReporteByKilometros() {
		List<MonopatinReporteDTO> reportes=new ArrayList<>();
		List<Monopatin> monopatines=this.monoRep.getAllMonopatinesByKilometro();
		for(Monopatin monopatin:monopatines) {
			reportes.add(this.mapToMonopatinReporteDTO(monopatin));
		}
		return reportes;
	}
	
	@Transactional
	public List<MonopatinReporteDTO> getReporteByUsoConPausas(){
		List<MonopatinReporteDTO> reportes=new ArrayList<>();
		List<Monopatin> monopatines=this.monoRep.getAllMonopatinesByUsoConPausas();
		for(Monopatin monopatin:monopatines) {
			reportes.add(this.mapToMonopatinReporteDTO(monopatin));
		}
		return reportes;
	}
	
	@Transactional
	public List<MonopatinReporteDTO> getReporteByUsoKilo(boolean conPausas) {
		List<MonopatinReporteDTO> reportes=this.getReporteByKilometros();
		if(!conPausas) {
			List<MonopatinReporteDTO> reportesSinPausas=new ArrayList<>();
			for(MonopatinReporteDTO reporte:reportes) {
				Duration pausa=this.viajeFeign.getPausasById(reporte.getId_monopatin()).getBody();
				reporte.setTiempoUso(reporte.getTiempoUso().minus(pausa));
				reportesSinPausas.add(reporte);
			}
			return reportesSinPausas;
		}
		return reportes;
	}
	
	@Transactional
	public List<MonopatinReporteDTO> getReporteByUsoSinPausas(){
		List<MonopatinReporteDTO> reportes=new ArrayList<>();
		List<Monopatin> monopatines=this.monoRep.getAllMonopatinesByUsoConPausas();
		for(Monopatin monopatin:monopatines) {
			Duration pausa=this.viajeFeign.getPausasById(monopatin.getId_monopatin()).getBody();
			monopatin.setTiempoUso(monopatin.getTiempoUso().minus(pausa));
			reportes.add(this.mapToMonopatinReporteDTO(monopatin));
			
		}
		return reportes;
	}
	
	public MonopatinReporteDTO mapToMonopatinReporteDTO(Monopatin m) {
		MonopatinReporteDTO reporte=new MonopatinReporteDTO();
		reporte.setId_monopatin(m.getId_monopatin());
		reporte.setEstado(m.getEstado());
		reporte.setHabilitado(m.isHabilitado());
		reporte.setLatitud(m.getLatitud());
		reporte.setLongitud(m.getLongitud());
		reporte.setKilometrosTotales(m.getKilometrosTotales());
		reporte.setTiempoUso(m.getTiempoUso());
		return reporte;
	}
	
	@Transactional
	public MonopatinResponseDTO getMonopatinCercano(double latitud, double longitud) {
		MonopatinResponseDTO response=new MonopatinResponseDTO();
		Pageable limite = PageRequest.of(0, 1);
		Monopatin patin=this.monoRep.getMonopatinCercano(latitud, longitud, "en espera", true, limite);
		if(patin==null) {
			response.setExito(false);
			response.setMensaje("no se encontro un monopatin cercano");
			return response;
		}
		response=this.mapToMonopatinResponseDTO(patin);
		return response;
	}
	
	@Transactional
	public MonopatinResponseDTO getAllMonopatinesCercanos(double latitud, double longitud) {
		MonopatinResponseDTO response=new MonopatinResponseDTO();
		Monopatin patin=this.monoRep.getAllMonopatinesCercanos(latitud, longitud, "en espera", true);
		if(patin==null) {
			response.setExito(false);
			response.setMensaje("no se encontro un monopatin cercano");
			return response;
		}
		response=this.mapToMonopatinResponseDTO(patin);
		return response;
	}
	
	@Transactional
	public MonopatinResponseDTO cambiarEstadoMonopatinEnMantenimiento(long id, String estado, boolean habilitado) {
		MonopatinResponseDTO response=new MonopatinResponseDTO();
		this.monoRep.changeMonopatinOnMaintenance(id, estado, habilitado);
		Monopatin patin=this.monoRep.findById(id).orElse(null);
		response=this.mapToMonopatinResponseDTO(patin);
		return response;
	}
	
	@Transactional
	public MonopatinResponseDTO añadirMonopatin(MonopatinRequestDTO request) {
		MonopatinResponseDTO response=new MonopatinResponseDTO();
		Monopatin patin=new Monopatin(request.getEstado(), request.getLatitud(), request.getLongitud());
		monoRep.save(patin);
		response=mapToMonopatinResponseDTO(patin);
		return response;
	}
	
	@Transactional(readOnly=true)
	public MonopatinResponseDTO getMonopatinById(long id) {
		MonopatinResponseDTO response=new MonopatinResponseDTO();
		Monopatin monopatin=monoRep.findById(id).orElse(null);
		if(monopatin==null) {
			response.setMensaje("el monopatin de id "+id+" no existe");
			response.setExito(false);
			return response;
		}
		response=mapToMonopatinResponseDTO(monopatin);
		return response;
	}
	
	public MonopatinResponseDTO mapToMonopatinResponseDTO(Monopatin m) {
		MonopatinResponseDTO response=new MonopatinResponseDTO();
		response.setEstado(m.getEstado());
		response.setHabilitado(m.isHabilitado());
		response.setLatitud(m.getLatitud());
		response.setLongitud(m.getLongitud());
		response.setKilometrosTotales(m.getKilometrosTotales());
		response.setTiempoUso(m.getTiempoUso());
		
		return response;
	}
}
