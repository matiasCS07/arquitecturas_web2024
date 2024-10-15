package Entidades;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrera;

    @Column
    private String nombreCarrera;

    @OneToMany (mappedBy = "carrera")
    private List<Inscripcion> inscripciones;

    public Carrera(){
        super();
    }

    public Carrera(String nombre, List<Inscripcion> inscripciones) {
        this.nombreCarrera = nombre;
        this.inscripciones = new ArrayList<>(inscripciones);
    }

    public String getNombre() {
        return nombreCarrera;
    }

    public void setNombre(String nombre) {
        this.nombreCarrera = nombre;
    }

    public List<Inscripcion> getInscripciones(){
        return this.inscripciones;
    }
    public void setInscripciones(List<Inscripcion> insc){
        this.inscripciones = insc;
    }


    public int getId() {
        return idCarrera;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                ", inscripciones=" + inscripciones +
                '}';
    }
}
