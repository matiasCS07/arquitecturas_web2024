package com.example.demo.entidades;


import jakarta.persistence.*;

@Entity
public class Inscripcion {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idInscripcion;

	@ManyToOne
	@JoinColumn(name="idEstudiante")
    private Estudiante estudiante;
	
	@ManyToOne
	@JoinColumn(name="idCarrera")
    private Carrera carrera;
	
	private int ingreso;
    private int antiguedad;
    private boolean graduado;

    public Inscripcion(){
        super();
    }

    public Inscripcion(Estudiante estudiante, Carrera carrera, int ingreso, int antiguedad, boolean graduado) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.ingreso=ingreso;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }


    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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

    public int getIdInscripcion() {
        return idInscripcion;
    }

	public int getIngreso() {
		return ingreso;
	}

	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}
}
