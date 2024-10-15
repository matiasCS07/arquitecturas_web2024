package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.EstudianteDTO;
import com.example.demo.entidades.Estudiante;
import com.example.demo.servicios.EstudianteSer;


@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
	@Autowired
	private EstudianteSer estSer; 
	
	@GetMapping
	public List<EstudianteDTO> getEstudiantes(@RequestParam(value = "sort", required = false) String campo){
		return this.estSer.getEstudiantes(campo);
	}
	
	@GetMapping(path="/genero/{genero}")
	public  List<EstudianteDTO> getEstudianteByGenero(@PathVariable String genero){
		return this.estSer.getEstudiantesByGenero(genero);
	}
	
	@GetMapping(path="/{id}")
	public Optional<Estudiante> getEstudianteById(@PathVariable int id){
		return this.estSer.getEstudianteById(id);
	}
	
	@GetMapping(path="/libreta/{libreta}")
	public Optional<Estudiante> getEstudianteByLibreta(@PathVariable int libreta){
		return this.estSer.getEstudianteByLibreta(libreta);
	}
	
	@GetMapping("/sede")
    public List<EstudianteDTO> obtenerEstudiantesPorCarreraYCiudad(@RequestParam(value="carrera", required=true) Integer idCarrera, @RequestParam(required=true) String ciudad) {
        return estSer.getEstudiantesPorCarreraYCiudad(idCarrera, ciudad);
    }
	
	@PostMapping
	public Estudiante saveEstudiante(@RequestBody Estudiante est) {
		return this.estSer.saveEstudiante(est);
	}
	
	@PostMapping(path="/list")
	public List<EstudianteDTO> saveEstudiantes(@RequestBody List<Estudiante>list) {
		return this.estSer.saveEstudiantes(list);
	}
	
	@PutMapping(path="/{id}")
	public Estudiante updateEstudiante(@RequestBody Estudiante est,@PathVariable int id) {
		return estSer.updateById(est, id);
	}
	
	@DeleteMapping(path="/{id}")
	public String deleteEstudianteById(@PathVariable int id) {
		boolean ok=this.estSer.deleteEstudianteById(id);
		
		if(ok) {
			return "Estudiante con id= "+id+" eliminada.";
		}else {
			return "Estudiante no eliminada.";
		}
	}
}
