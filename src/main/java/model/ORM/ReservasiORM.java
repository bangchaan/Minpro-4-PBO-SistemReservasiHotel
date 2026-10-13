package model.ORM;

import config.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import model.Reservasi;
public class ReservasiORM {

    // CREATE
    public boolean tambahReservasi(Reservasi reservasi) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(reservasi);      
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Gagal menambah reservasi (ORM) - " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    // READ (by ID)
    public Reservasi cariReservasiById(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            // find() otomatis menghasilkan ReservasiStandard atau ReservasiVIP
            return em.find(Reservasi.class, id);
        } catch (Exception e) {
            System.out.println("Gagal mencari reservasi (ORM) - " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    // READ (all)
    public ArrayList<Reservasi> getAllReservasi() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return new ArrayList<>(em.createQuery(
                    "SELECT r FROM Reservasi r ORDER BY r.idReservasi", Reservasi.class)
                    .getResultList());
        } catch (Exception e) {
            System.out.println("Gagal mengambil data reservasi (ORM) - " + e.getMessage());
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    // UPDATE
    public boolean updateReservasi(Reservasi reservasi) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            if (em.find(Reservasi.class, reservasi.getIdReservasi()) == null) {
                return false;
            }

            em.getTransaction().begin();
            em.merge(reservasi);        
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Gagal mengubah reservasi (ORM) - " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    // DELETE
    public boolean hapusReservasi(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            Reservasi r = em.find(Reservasi.class, id);
            if (r == null) {
                return false;
            }

            em.getTransaction().begin();
            em.remove(r);               
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Gagal menghapus reservasi (ORM) - " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
}