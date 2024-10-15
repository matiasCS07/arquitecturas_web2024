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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CarreraDTO;
import com.example.demo.dtos.ReporteDTO;
import com.example.demo.entidades.Carrera;
import com.example.demo.servicios.CarreraSer;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

	@Autowired
	private CarreraSer carreraSer; 
	
	@GetMapping
	public List<CarreraDTO> getCarreras(){
		return this.carreraSer.getCarreras();
	}
	
	@GetMapping(path="/{id}")
	public Optional<Carrera> getCarreraById(@PathVariable int id){
		return this.carreraSer.getCarreraById(id);
	}
	
	@GetMapping(path="/inscriptos")
	public List<CarreraDTO> getCarreraConInscriptos(){
		return this.carreraSer.getCarreraConInscriptos();
	}
	
	@GetMapping(path="/reporte")
	public List<ReporteDTO> getReporteCarreras(){
		return this.carreraSer.getReporteCarreras();
	}
	
	@PostMapping
	public Carrera saveCarrera(@RequestBody Carrera carr) {
		return this.carreraSer.saveCarrera(carr);
	}
	
	@PostMapping(path="/list")
	public List<CarreraDTO> saveCarreras(@RequestBody List<Carrera>list) {
		return this.carreraSer.saveCarreras(list);
	}
	
	@PutMapping(path="/{id}")
	public Carrera updateCarrera(@RequestBody Carrera carr,@PathVariable int id) {
		return carreraSer.updateById(carr, id);
	}
	
	@DeleteMapping(path="/{id}")
	public String deleteCarreraById(@PathVariable int id) {
		boolean ok=this.carreraSer.deleteCarreraById(id);
		
		if(ok) {
			return "Carrera con id= "+id+" eliminada.";
		}else {
			return "Carrera no eliminada.";
		}
	}
}
