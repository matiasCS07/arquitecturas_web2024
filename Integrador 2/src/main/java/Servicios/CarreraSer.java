package Servicios;
import Entidades.Carrera;
import javax.persistence.*;
import java.util.List;

public class CarreraSer {
    private EntityManagerFactory emf;

    public CarreraSer(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void createCarrera(Carrera carrera) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(carrera);
            tx.commit();
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

    public List<Carrera> getCarreras() {
        EntityManager em = this.emf.createEntityManager();

        List var2;
        try {
            var2 = em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
        } finally {
            em.close();
        }

        return var2;
    }

    public Carrera getCarreraById(Long id) {
        EntityManager em = this.emf.createEntityManager();

        Carrera var3;
        try {
            var3 = (Carrera)em.find(Carrera.class, id);
        } finally {
            em.close();
        }

        return var3;
    }

    public void updateCarrera(Carrera carreraExistente, Carrera carreraNueva) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Carrera carrera = (Carrera)em.find(Carrera.class, carreraExistente.getId());
            if (carrera != null) {
                carrera.setNombre(carreraNueva.getNombre());
                em.merge(carrera);
                tx.commit();
                System.out.println("Carrera modificada correctamente");
            } else {
                System.out.println("La carrera no fue encontrada.");
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

    public void deleteCarrera(Carrera carrera) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            if (carrera != null) {
                tx.begin();
                em.remove(carrera);
                tx.commit();
                System.out.println("Carrera borrada con exito");
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

    public List<Object[]> getCarrerasByInscriptos() {
        EntityManager em = this.emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("SELECT c.nombreCarrera, COUNT(i.idInscripcion) AS inscriptos FROM Inscripcion i JOIN i.carrera c GROUP BY c.nombreCarrera ORDER BY inscriptos DESC", Object[].class);
        return query.getResultList();
    }
}
