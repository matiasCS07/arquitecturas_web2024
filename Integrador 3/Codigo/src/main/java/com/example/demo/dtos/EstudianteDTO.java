package com.example.demo.dtos;

import java.util.ArrayList;
import java.util.List;

public class EstudianteDTO {
    private Integer idEstudiante;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private int documento;
    private String ciudadResidencia;
    private int libretaUniversitaria;
    private List<String> inscripciones;

    public EstudianteDTO(){
        super();
    }

    public EstudianteDTO(Integer id, String nombre, String apellido, int edad, int documento, String genero, String ciudadResidencia, int libretaUniversitaria, List<String> inscripciones) {
    	this.idEstudiante=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
        this.genero = genero;
        this.ciudadResidencia = ciudadResidencia;
        this.libretaUniversitaria = libretaUniversitaria;
        this.inscripciones = new ArrayList<>(inscripciones);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public int getLibretaUniversitaria() {
        return libretaUniversitaria;
    }

    public void setLibretaUniversitaria(int libretaUniversitaria) {
        this.libretaUniversitaria = libretaUniversitaria;
    }

    public List<String> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<String> inscripciones) {
        this.inscripciones = new ArrayList<String>(inscripciones);
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }
    
    public Integer setId(Integer id) {
    	return this.idEstudiante=id;
    }
}
