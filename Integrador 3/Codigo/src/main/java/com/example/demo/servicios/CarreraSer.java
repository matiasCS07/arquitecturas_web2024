package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CarreraDTO;
import com.example.demo.dtos.ReporteDTO;
import com.example.demo.entidades.Carrera;
import com.example.demo.repositories.CarreraRepository;

@Service
public class CarreraSer {
    @Autowired
    private CarreraRepository carreraRepository;

	public CarreraDTO convertToCarreraDTO(Carrera carr) {
	    int inscripcionesCount = (carr.getInscripciones() != null) ? carr.getInscripciones().size() : 0;
	    return new CarreraDTO(carr.getIdCarrera(), carr.getNombreCarrera(), inscripcionesCount);
	}

    public List<CarreraDTO> getCarreras(){
    	List<Carrera> carreras=(ArrayList<Carrera>)carreraRepository.findAll();
        return carreras.stream()
                .map(this::convertToCarreraDTO)
                .collect(Collectors.toList());
    }
    
    public List<CarreraDTO> getCarreraConInscriptos(){
    	List<CarreraDTO>carreras=new ArrayList<>();
    	List<Object[]>resultado=carreraRepository.findCarrerasConInscriptos();
    	for(Object[] elemento:resultado) {
    		CarreraDTO carrDTO=this.convertToCarreraDTO((Carrera)elemento[0]);
    		carrDTO.setInscripciones(((Number)elemento[1]).intValue());
    		carreras.add(carrDTO);
    	}
    	return carreras;
    }
    
    public List<ReporteDTO> getReporteCarreras() {
        List<Object[]> resultado=carreraRepository.findReporteCarreras();
        List<ReporteDTO> reporte=new ArrayList<ReporteDTO>();
        for(Object[] row:resultado) {
        	reporte.add(new ReporteDTO((String) row[0], ((Number) row[1]).intValue(), ((Number) row[2]).intValue()));
        }
        return reporte;
    }
    
    public Carrera saveCarrera(Carrera carrera) {
    	return carreraRepository.save(carrera);
    	
    }
    
    public List<CarreraDTO> saveCarreras(List<Carrera>list){
    	List<Carrera> guardados=carreraRepository.saveAll(list);
    	return guardados.stream()
                .map(this::convertToCarreraDTO)
                .collect(Collectors.toList());
    }
    
    
    public Optional<Carrera> getCarreraById(Integer id){
    	return carreraRepository.findById(id);
    }
    
    public Carrera updateById(Carrera modificada, Integer id) {
    	Carrera carr=this.getCarreraById(id).get();
    	carr.setNombreCarrera(modificada.getNombreCarrera());
    	carr.setInscripciones(modificada.getInscripciones());
    	
    	carreraRepository.save(carr);
    	
    	return carr;
    }
    
    public Boolean deleteCarreraById(Integer id) {
    	try {
    		carreraRepository.deleteById(id);
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }
}
