package Servicios;
import Entidades.Estudiante;
import javax.persistence.*;
import java.util.List;


public class EstudianteSer {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");

    public EstudianteSer() {
    }

    public void insertEstudiante(Estudiante estudiante) {
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
        em.close();
    }

    public void updateEstudiante(Estudiante estudianteExistente, Estudiante estudianteNuevo) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Estudiante estudiante = (Estudiante)em.find(Estudiante.class, estudianteExistente.getIdEstudiante());
            if (estudiante != null) {
                estudiante.setNombre(estudianteNuevo.getNombre());
                estudiante.setApellido(estudianteNuevo.getApellido());
                estudiante.setEdad(estudianteNuevo.getEdad());
                estudiante.setGenero(estudianteNuevo.getGenero());
                estudiante.setDocumento(estudianteNuevo.getDocumento());
                estudiante.setCiudadResidencia(estudianteNuevo.getCiudadResidencia());
                estudiante.setLibretaUniversitaria(estudianteNuevo.getLibretaUniversitaria());
                em.merge(estudiante);
                tx.commit();
                System.out.println("Estudiante Cambiado");
            } else {
                System.out.println("El estudiante no fue encontrado");
            }
        } catch (Exception var9) {
            Exception e = var9;
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public void deleteEstudiante(Estudiante estudiante) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            if (estudiante != null) {
                tx.begin();
                em.remove(estudiante);
                tx.commit();
                System.out.println("Estudiante eliminado exitosamente");
            } else {
                System.out.println("El estudiante no existe.");
            }
        } catch (Exception var8) {
            Exception e = var8;
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public Estudiante getEstudianteByLibreta(int numeroLibreta) {
        EntityManager em = this.emf.createEntityManager();

        Estudiante var3;
        try {
            var3 = (Estudiante)em.createQuery("SELECT e FROM Estudiante e WHERE e.libretaUniversitaria = :numeroLibreta", Estudiante.class).setParameter("numeroLibreta", numeroLibreta).getSingleResult();
        } finally {
            em.close();
        }

        return var3;
    }

    public List<Estudiante> getEstudianteByGenero(String genero) {
        EntityManager em = this.emf.createEntityManager();

        List var4;
        try {
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class);
            query.setParameter("genero", genero);
            var4 = query.getResultList();
        } finally {
            em.close();
        }

        return var4;
    }

    public List<Estudiante> getEstudiantesByEdad() {
        EntityManager em = this.emf.createEntityManager();

        List var3;
        try {
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e ORDER BY e.edad ASC", Estudiante.class);
            var3 = query.getResultList();
        } finally {
            em.close();
        }

        return var3;
    }

    public List<Estudiante> getEstudianteByCarreraAndCiudad(String carreraNombre, String ciudad) {
        EntityManager em = this.emf.createEntityManager();
        TypedQuery<Estudiante> query = em.createQuery(
                "SELECT i.estudiante FROM Inscripcion i WHERE i.carrera.nombreCarrera = :carrera AND i.estudiante.ciudadResidencia = :ciudad",
                Estudiante.class
        );
        query.setParameter("carrera", carreraNombre);
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }

}
