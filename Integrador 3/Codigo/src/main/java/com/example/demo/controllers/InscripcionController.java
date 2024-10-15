package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.InscripcionDTO;
import com.example.demo.entidades.Inscripcion;
import com.example.demo.servicios.InscripcionSer;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
	@Autowired
	private InscripcionSer inscSer; 
	
	@GetMapping
	public List<InscripcionDTO> getInscripciones(){
		return this.inscSer.getInscripcion();
	}
	
	@PostMapping
	public InscripcionDTO saveInscripcion(@RequestBody Inscripcion insc) {
		return this.inscSer.saveInscripcion(insc);
	}
	
	@PostMapping(path="/list")
	public List<InscripcionDTO> saveInscripciones(@RequestBody List<Inscripcion>list) {
		return this.inscSer.saveInscripciones(list);
	}
	
	@GetMapping(path="/{id}")
	public InscripcionDTO getInscripcionById(@PathVariable int id){
		return this.inscSer.getInscripcionDTOById(id);
	}
	
	@PutMapping(path="/{id}")
	public Inscripcion updateInscripcion(@RequestBody Inscripcion insc,@PathVariable int id) {
		return inscSer.updateById(insc, id);
	}
	
	@DeleteMapping(path="/{id}")
	public String deleteCInscripcionById(@PathVariable int id) {
		boolean ok=this.inscSer.deleteInscripcionById(id);
		
		if(ok) {
			return "Inscripcion con id= "+id+" eliminada.";
		}else {
			return "Inscripcion no eliminada.";
		}
	}
}
