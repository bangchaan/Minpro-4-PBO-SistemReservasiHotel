package config;

import java.util.ArrayList;
import java.util.Scanner;
import model.Reservasi;
import model.ORM.ReservasiORM;
import model.ReservasiVIP;


public class TestORM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservasiORM dao = new ReservasiORM();

        System.out.println("=== TEST ORM (Hibernate/JPA) ===");

        // 1. CREATE
        Reservasi baru = new ReservasiVIP(0, "Tamu Uji ORM", 2, "Reguler");
        if (dao.tambahReservasi(baru)) {
            System.out.println("[CREATE] Berhasil. ID baru = " + baru.getIdReservasi());
        } else {
            System.out.println("[CREATE] GAGAL. Pengujian dihentikan.");
            JPAUtil.tutup();
            return;
        }
        int id = baru.getIdReservasi();
        jeda(scanner, "Cek phpMyAdmin: harus ada baris baru 'Tamu Uji ORM'.");

        // 2. READ
        System.out.println("[READ] Semua data di database:");
        ArrayList<Reservasi> semua = dao.getAllReservasi();
        for (Reservasi r : semua) {
            cetak(r);
        }

        Reservasi hasilCari = dao.cariReservasiById(id);
        if (hasilCari == null) {
            System.out.println("[READ] Cari ID " + id + ": TIDAK DITEMUKAN. Pengujian dihentikan.");
            JPAUtil.tutup();
            return;
        }
        System.out.println("[READ] Cari ID " + id + ":");
        cetak(hasilCari);

        // 3. UPDATE
        hasilCari.setNamaTamu("Tamu Uji ORM (diubah)");
        hasilCari.setJumlahMalam(5);
        if (hasilCari instanceof ReservasiVIP) {
            ((ReservasiVIP) hasilCari).setJenisPenyambutan("Airport Pickup + Lounge");
        }
        if (dao.updateReservasi(hasilCari)) {
            System.out.println("[UPDATE] Berhasil. Data setelah diubah:");
            cetak(dao.cariReservasiById(id));
        } else {
            System.out.println("[UPDATE] GAGAL.");
        }
        jeda(scanner, "Cek phpMyAdmin: nama, jumlah malam, dan penyambutan harus berubah.");

        // 4. DELETE
        if (dao.hapusReservasi(id)) {
            System.out.println("[DELETE] Berhasil menghapus ID " + id);
        } else {
            System.out.println("[DELETE] GAGAL.");
        }
        if (dao.cariReservasiById(id) == null) {
            System.out.println("[DELETE] Cari ulang ID " + id + ": tidak ada (benar-benar terhapus).");
        } else {
            System.out.println("[DELETE] Cari ulang ID " + id + ": MASIH ADA (ada masalah).");
        }

        System.out.println("=== TEST ORM SELESAI ===");
        JPAUtil.tutup();
        scanner.close();
    }

    private static void cetak(Reservasi r) {
        System.out.println("   " + r.getIdReservasi() + " | " + r.getTipe() + " | "
                + r.getNamaTamu() + " | " + r.getJumlahMalam() + " malam | "
                + r.getStatus() + " | Rp" + r.hitungTotalBiaya());
    }

    private static void jeda(Scanner scanner, String pesan) {
        System.out.println(">> " + pesan);
        System.out.print(">> Tekan Enter untuk lanjut...");
        scanner.nextLine();
    }
}