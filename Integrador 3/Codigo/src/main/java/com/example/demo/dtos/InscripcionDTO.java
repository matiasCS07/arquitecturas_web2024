package com.example.demo.dtos;

public class InscripcionDTO {
    private Integer idInscripcion;
    private String estudiante;
    private String carrera;
	private int ingreso;
    private int antiguedad;
    private boolean graduado;

    public InscripcionDTO(){
        super();
    }

    public InscripcionDTO(Integer id, String estudiante, String carrera, int ingreso, int antiguedad, boolean graduado) {
    	this.idInscripcion=id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.ingreso = ingreso;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }


    public void setEstudiante(String estudiante) {
		this.estudiante = estudiante;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public int getIngreso() {
		return ingreso;
	}

	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}

	public String getEstudiante() {
        return estudiante;
    }

    public String getCarrera() {
        return carrera;
    }


    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    public Integer getIdInscripcion() {
        return idInscripcion;
    }
    
    public void setIdInscripcion(Integer id) {
        this.idInscripcion=id;
    }
}
