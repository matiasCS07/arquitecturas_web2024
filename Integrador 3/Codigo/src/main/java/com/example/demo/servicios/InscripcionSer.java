package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.InscripcionDTO;
import com.example.demo.entidades.Inscripcion;
import com.example.demo.repositories.InscripcionRepository;

@Service
public class InscripcionSer {

	@Autowired
    private InscripcionRepository inscRepository;

	public InscripcionDTO convertToInscripcionDTO(Inscripcion insc) {
	    String apellidoEstudiante = (insc.getEstudiante() != null) ? insc.getEstudiante().getApellido() : "Sin apellido";
	    String nombreCarrera = (insc.getCarrera() != null) ? insc.getCarrera().getNombreCarrera() : "Sin carrera";
	    int ingreso = insc.getIngreso();
	    
		return new InscripcionDTO(insc.getIdInscripcion(), apellidoEstudiante, nombreCarrera, ingreso, insc.getAntiguedad(), insc.isGraduado());
	}
	
    public List<InscripcionDTO> getInscripcion(){
    	List<Inscripcion> inscripciones=(ArrayList<Inscripcion>)inscRepository.findAll();
        return inscripciones.stream()
                .map(this::convertToInscripcionDTO)
                .collect(Collectors.toList());
    }
    
    public InscripcionDTO saveInscripcion(Inscripcion insc) {
    	Inscripcion guardado=inscRepository.save(insc);
    	return this.convertToInscripcionDTO(guardado);
    }
    
    public List<InscripcionDTO> saveInscripciones(List<Inscripcion>list){
    	List<Inscripcion> guardados=inscRepository.saveAll(list);
    	return guardados.stream()
                .map(this::convertToInscripcionDTO)
                .collect(Collectors.toList());
    }
    
    
    public InscripcionDTO getInscripcionDTOById(Integer id){
    	Inscripcion insc=inscRepository.findById(id).get();
    	return this.convertToInscripcionDTO(insc);
    }
    
    private Optional<Inscripcion> getInscripcionById(Integer id){
    	return inscRepository.findById(id);
    }
    
    public Inscripcion updateById(Inscripcion modificada, Integer id) {
    	Inscripcion insc=this.getInscripcionById(id).get();
    	insc.setEstudiante(modificada.getEstudiante());
    	insc.setAntiguedad(modificada.getAntiguedad());
    	insc.setCarrera(modificada.getCarrera());
    	insc.setGraduado(modificada.isGraduado());
    	
    	inscRepository.save(insc);
    	
    	return insc;
    }
    
    public Boolean deleteInscripcionById(Integer id) {
    	try {
    		inscRepository.deleteById(id);
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }
}
