package com.mycompany.reservasihotel;

import config.Koneksi;
import controller.ReservasiController;
import java.util.Scanner;
import view.ReservasiView;

public class SistemReservasiHotel {

    public static void main(String[] args) {

        if (Koneksi.getConnection() != null) {
            Scanner scanner = new Scanner(System.in);

            ReservasiView view = new ReservasiView();
            ReservasiController controller = new ReservasiController(view, scanner);

            controller.jalankan();

            scanner.close();
        } else {
            System.out.println("Gagal terhubung ke Database. Program dihentikan.");
        }
    }
}