package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.EstudianteDTO;
import com.example.demo.entidades.Estudiante;
import com.example.demo.entidades.Inscripcion;
import com.example.demo.repositories.EstudianteRepository;

@Service
public class EstudianteSer {

	@Autowired
    private EstudianteRepository estRepository;
    
	
	public EstudianteDTO convertToEstudianteDTO(Estudiante est) {
		List<String> inscripcionesNombre = new ArrayList<String>();
		for(Inscripcion e:est.getInscripciones()) {
			inscripcionesNombre.add(e.getCarrera().getNombreCarrera());
		}
		
	    return new EstudianteDTO(est.getIdEstudiante(), est.getNombre(), est.getApellido(), est.getEdad(), est.getDocumento(), est.getGenero(), est.getCiudadResidencia(), est.getLibretaUniversitaria(), inscripcionesNombre);
	}
	
    public List<EstudianteDTO> getEstudiantes(String sort){
    	Sort sortOrder = Sort.by(sort != null ? sort : "idEstudiante");
    	List<Estudiante> estudiantes=(ArrayList<Estudiante>)estRepository.findAll(sortOrder);
    	return estudiantes.stream()
    			.map(this::convertToEstudianteDTO)
    			.collect(Collectors.toList());
    }
    
    public List<EstudianteDTO> getEstudiantesByGenero(String genero){
    	List<Estudiante> estudiantes=estRepository.findAllByGenero(genero);
    	return estudiantes.stream().map(this::convertToEstudianteDTO).collect(Collectors.toList());
    }
    
    public Optional<Estudiante> getEstudianteById(Integer id){
    	return estRepository.findById(id);
    }
    
    public Optional<Estudiante> getEstudianteByLibreta(int libreta) {
        return estRepository.findByLibretaUniversitaria(libreta);
    }
    
    public List<EstudianteDTO> getEstudiantesPorCarreraYCiudad(Integer idCarrera, String ciudad) {
        List<Estudiante> estudiantes = estRepository.findByCarreraAndCiudad(idCarrera, ciudad);
        return estudiantes.stream()
                .map(this::convertToEstudianteDTO)
                .collect(Collectors.toList());
    }
    
    public Estudiante saveEstudiante(Estudiante estudiante) {
    	return estRepository.save(estudiante);
    }
    
    public List<EstudianteDTO> saveEstudiantes(List<Estudiante>list){
    	List<Estudiante> estudiantes=estRepository.saveAll(list);
    	return estudiantes.stream().map(this::convertToEstudianteDTO).collect(Collectors.toList());
    }
    
    public Estudiante updateById(Estudiante modificada, Integer id) {
    	Estudiante est=this.getEstudianteById(id).get();
    	est.setNombre(modificada.getNombre());
    	est.setInscripciones(modificada.getInscripciones());
    	est.setApellido(modificada.getApellido());
    	est.setCiudadResidencia(modificada.getCiudadResidencia());
    	est.setDocumento(modificada.getDocumento());
    	est.setEdad(modificada.getEdad());
    	est.setGenero(modificada.getGenero());
    	est.setLibretaUniversitaria(modificada.getLibretaUniversitaria());
    	
    	estRepository.save(est);
    	
    	return est;
    }
    
    public Boolean deleteEstudianteById(Integer id) {
    	try {
    		estRepository.deleteById(id);
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }
}
