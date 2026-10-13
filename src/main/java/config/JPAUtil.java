package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("reservasiPU");
        }
        return emf.createEntityManager();
    }

    public static void tutup() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}