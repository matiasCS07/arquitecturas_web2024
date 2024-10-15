package Servicios;
import javax.persistence.*;
import Entidades.Carrera;
import Entidades.Estudiante;
import Entidades.Inscripcion;
import java.util.List;

public class InscripcionSer {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");

    public InscripcionSer() {
    }

    public void crearInscripcion(Estudiante estudiante, Carrera carrera, int antiguedad, boolean estadoGraduacion) {
        EntityManager em = this.emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setEstudiante(estudiante);
            inscripcion.setCarrera(carrera);
            inscripcion.setAntiguedad(antiguedad);
            inscripcion.setGraduado(estadoGraduacion);
            em.persist(inscripcion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public List<Inscripcion> obtenerInscripciones() {
        EntityManager em = this.emf.createEntityManager();

        List var3;
        try {
            TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class);
            var3 = query.getResultList();
        } finally {
            em.close();
        }

        return var3;
    }

    public Inscripcion obtenerInscripcionPorId(int idInscripcion) {
        EntityManager em = this.emf.createEntityManager();

        Inscripcion var3;
        try {
            var3 = (Inscripcion)em.find(Inscripcion.class, idInscripcion);
        } finally {
            em.close();
        }

        return var3;
    }

    public void matricularEstudiante(Estudiante estudiante, Carrera carrera, int antiguedad) {
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCarrera(carrera);
        inscripcion.setAntiguedad(antiguedad);
        inscripcion.setGraduado(false);
        em.persist(inscripcion);
        em.getTransaction().commit();
        em.close();
    }
    public List<Object[]> getReporteCarreras() {
        EntityManager em = this.emf.createEntityManager();

        try {
            List<Object[]> result = em.createQuery(
                    "SELECT i.carrera.nombreCarrera, i.antiguedad, " +
                            "SUM(CASE WHEN i.graduado = true THEN 1 ELSE 0 END) AS egresados, " +
                            "SUM(CASE WHEN i.graduado = false THEN 1 ELSE 0 END) AS inscriptos " +
                            "FROM Inscripcion i " +
                            "GROUP BY i.carrera.nombreCarrera, i.antiguedad " +
                            "ORDER BY i.carrera.nombreCarrera ASC, i.antiguedad ASC",
                    Object[].class
            ).getResultList();
            return result;
        } finally {
            em.close();
        }
    }

}
