package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VIP")
public class ReservasiVIP extends Reservasi {

    @Column(name = "jenis_penyambutan")
    private String jenisPenyambutan;

    @Column(name = "biaya_penyambutan")
    private int biayaPenyambutan;

    public ReservasiVIP() {
    }

    public ReservasiVIP(int idReservasi, String namaTamu,
                        int jumlahMalam, String jenisPenyambutan) {
        super(idReservasi, namaTamu, jumlahMalam, 750000);
        setJenisPenyambutan(jenisPenyambutan);
    }

    public String getJenisPenyambutan() {
        return jenisPenyambutan;
    }

    public void setJenisPenyambutan(String jenisPenyambutan) {
        this.jenisPenyambutan = jenisPenyambutan;

        if (jenisPenyambutan.equalsIgnoreCase("Reguler")) {
            biayaPenyambutan = 0;
        } else if (jenisPenyambutan.equalsIgnoreCase("Executive Lounge")) {
            biayaPenyambutan = 100000;
        } else if (jenisPenyambutan.equalsIgnoreCase(
                "Airport Pickup + Lounge")) {
            biayaPenyambutan = 200000;
        }
    }

    public int getBiayaPenyambutan() {
        return biayaPenyambutan;
    }

    @Override
    public String getTipe() {
        return "VIP";
    }

    @Override
    public String getDetailTambahan() {
        return "Penyambutan: " + jenisPenyambutan
                + " (Rp" + biayaPenyambutan + ")";
    }

    @Override
    public int hitungTotalBiaya() {
        return super.hitungTotalBiaya() + biayaPenyambutan;
    }
}