import javax.persistence.*;

public class SQL {
    public SQL(){

    }
    public void dropAllTables(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
            em.createNativeQuery("DROP TABLE IF EXISTS estudiante, carrera, inscripcion").executeUpdate();
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
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



}
