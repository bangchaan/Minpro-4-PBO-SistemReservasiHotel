package controller;

import java.util.Scanner;
import model.DapatDidenda;
import model.Reservasi;
import model.DAO.ReservasiDAO;
import model.ReservasiStandard;
import model.ReservasiVIP;
import view.ReservasiView;

public class ReservasiController {

    private static final int MAKS_ID = 100000;
    private static final int MAKS_NAMA = 25;

    private ReservasiDAO dao = new ReservasiDAO();
    private ReservasiView view;
    private Scanner scanner;

    public ReservasiController(ReservasiView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
    }

    public void jalankan() {
        boolean berjalan = true;

        while (berjalan) {
            view.tampilkanMenu();
            int pilihan = inputAngka("Pilih menu: ", 0, 7);

            switch (pilihan) {
                case 1:
                    tambahReservasi();
                    break;
                case 2:
                    view.tampilkanDaftar(dao.getAllReservasi());
                    break;
                case 3:
                    checkInReservasi();
                    break;
                case 4:
                    checkOutReservasi();
                    break;
                case 5:
                    updateReservasi();
                    break;
                case 6:
                    hapusReservasi();
                    break;
                case 7:
                    filterReservasi();
                    break;
                case 0:
                    berjalan = false;
                    view.tampilkanPesan("Terima kasih!");
                    break;
            }
        }
    }

    private void tambahReservasi() {
        view.tampilkanPesan("\n--- TAMBAH RESERVASI ---");
        view.tampilkanPesan("1. Standard (Rp300.000/malam, denda batal 20%)");
        view.tampilkanPesan("2. VIP (Rp750.000/malam + biaya penyambutan)");

        int tipe = inputAngka("Pilih tipe (1-2): ", 1, 2);
        String nama = inputTeks("Nama tamu: ");
        int malam = inputAngka("Jumlah malam (1-30): ", 1, 30);

        Reservasi baru;

        if (tipe == 1) {
            baru = new ReservasiStandard(0, nama, malam);
        } else {
            baru = new ReservasiVIP(0, nama, malam, pilihJenisPenyambutan());
        }

        if (dao.tambahReservasi(baru)) {
            view.tampilkanPesan(">> Reservasi berhasil! ID: " + baru.getIdReservasi());
        } else {
            view.tampilkanPesan(">> Gagal menyimpan reservasi ke database.");
        }
    }

    private void checkInReservasi() {
        int id = inputAngka("ID reservasi: ", 1, MAKS_ID);
        Reservasi r = dao.cariReservasiById(id);

        if (r == null) {
            view.tampilkanPesan(">> ID tidak ditemukan.");
        } else if (!Reservasi.MENUNGGU.equals(r.getStatus())) {
            view.tampilkanPesan(">> Gagal: status bukan Menunggu.");
        } else {
            r.checkIn();
            simpanPerubahan(r, "Check-in");
        }
    }

    private void checkOutReservasi() {
        int id = inputAngka("ID reservasi: ", 1, MAKS_ID);
        Reservasi r = dao.cariReservasiById(id);

        if (r == null) {
            view.tampilkanPesan(">> ID tidak ditemukan.");
        } else if (!Reservasi.CHECK_IN.equals(r.getStatus())) {
            view.tampilkanPesan(">> Gagal: tamu belum check-in.");
        } else {
            r.checkOut();
            simpanPerubahan(r, "Check-out");
        }
    }

    private void updateReservasi() {
        int id = inputAngka("ID reservasi: ", 1, MAKS_ID);
        Reservasi r = dao.cariReservasiById(id);

        if (r == null) {
            view.tampilkanPesan(">> ID tidak ditemukan.");
            return;
        }

        if (Reservasi.CHECK_OUT.equals(r.getStatus())) {
            view.tampilkanPesan(">> Gagal: tamu sudah check-out.");
            return;
        }

        boolean vip = r instanceof ReservasiVIP;

        view.tampilkanPesan("\n--- UPDATE RESERVASI ---");
        view.tampilkanPesan("1. Update Nama");
        view.tampilkanPesan("2. Update Jumlah Malam");
        if (vip) {
            view.tampilkanPesan("3. Update Layanan Tambahan");
        }
        view.tampilkanPesan("0. Kembali");

        int batas = vip ? 3 : 2;
        int pilihan = inputAngka("Pilih: ", 0, batas);

        switch (pilihan) {
            case 1:
                r.setNamaTamu(inputTeks("Nama baru: "));
                simpanPerubahan(r, "Nama");
                break;
            case 2:
                r.setJumlahMalam(inputAngka("Jumlah malam baru (1-30): ", 1, 30));
                simpanPerubahan(r, "Jumlah malam");
                break;
            case 3:
                ((ReservasiVIP) r).setJenisPenyambutan(pilihJenisPenyambutan());
                simpanPerubahan(r, "Layanan tambahan");
                break;
            case 0:
                break;
        }
    }

    private void hapusReservasi() {
        int id = inputAngka("ID reservasi: ", 1, MAKS_ID);
        Reservasi r = dao.cariReservasiById(id);

        if (r == null) {
            view.tampilkanPesan(">> ID tidak ditemukan.");
            return;
        }

        // Pertama dicek apakah reservasi bisa didenda (interface DapatDidenda)
        if (r instanceof DapatDidenda) {
            // Lalu dicek apakah belum check-out
            if (!Reservasi.CHECK_OUT.equals(r.getStatus())) {
                int denda = ((DapatDidenda) r).hitungDenda();
                view.tampilkanPesan(">> Denda pembatalan: Rp" + String.format("%,d", denda));
            }
        }

        if (dao.hapusReservasi(id)) {
            view.tampilkanPesan(">> Reservasi berhasil dihapus.");
        } else {
            view.tampilkanPesan(">> Gagal menghapus reservasi dari database.");
        }
    }

    private void filterReservasi() {
        view.tampilkanPesan("\n--- PILIH STATUS ---");
        view.tampilkanPesan("1. Menunggu");
        view.tampilkanPesan("2. Check-In");
        view.tampilkanPesan("3. Check-Out");
        view.tampilkanPesan("0. Kembali");

        int pilihan = inputAngka("Pilih status: ", 0, 3);
        String status;

        switch (pilihan) {
            case 1:
                status = Reservasi.MENUNGGU;
                break;
            case 2:
                status = Reservasi.CHECK_IN;
                break;
            case 3:
                status = Reservasi.CHECK_OUT;
                break;
            default:
                return; 
        }

        view.tampilkanDaftar(dao.getAllReservasi(), status);
    }


    private String pilihJenisPenyambutan() {
        view.tampilkanPesan("1. Reguler");
        view.tampilkanPesan("2. Executive Lounge (+Rp100.000)");
        view.tampilkanPesan("3. Airport Pickup + Lounge (+Rp200.000)");

        int pilihan = inputAngka("Pilih layanan (1-3): ", 1, 3);

        switch (pilihan) {
            case 2:
                return "Executive Lounge";
            case 3:
                return "Airport Pickup + Lounge";
            default:
                return "Reguler";
        }
    }

    private void simpanPerubahan(Reservasi r, String bagian) {
        if (dao.updateReservasi(r)) {
            view.tampilkanPesan(">> " + bagian + " berhasil diperbarui.");
        } else {
            view.tampilkanPesan(">> Gagal memperbarui data di database.");
        }
    }

    // Validasi angka tanpa try-catch: hasNextInt() mengecek dulu sebelum dibaca
    public int inputAngka(String pesan, int min, int max) {
        while (true) {
            System.out.print(pesan);

            if (scanner.hasNextInt()) {
                int angka = scanner.nextInt();
                scanner.nextLine();

                if (angka >= min && angka <= max) {
                    return angka;
                }

                view.tampilkanPesan(">> Angka harus " + min + " sampai " + max + ".");
            } else {
                scanner.nextLine();
                view.tampilkanPesan(">> Input harus berupa angka.");
            }
        }
    }

    private String inputTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = scanner.nextLine().trim();

            if (teks.isEmpty()) {
                view.tampilkanPesan(">> Input tidak boleh kosong.");
            } else if (teks.length() > MAKS_NAMA) {
                view.tampilkanPesan(">> Nama maksimal " + MAKS_NAMA + " karakter.");
            } else {
                return teks;
            }
        }
    }
}