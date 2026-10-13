package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Standard")
public class ReservasiStandard extends Reservasi
        implements DapatDidenda {

    @Column(name = "denda_persen")
    private int dendaPersen = 20;

    public ReservasiStandard() {
    }

    public ReservasiStandard(int idReservasi, String namaTamu,
                             int jumlahMalam) {
        super(idReservasi, namaTamu, jumlahMalam, 300000);
    }

    public int getDendaPersen() {
        return dendaPersen;
    }

    public void setDendaPersen(int dendaPersen) {
        this.dendaPersen = dendaPersen;
    }

    @Override
    public String getTipe() {
        return "Standard";
    }

    @Override
    public String getDetailTambahan() {
        return "Denda pembatalan: " + dendaPersen + "%";
    }

    @Override
    public int hitungDenda() {
        return hitungTotalBiaya() * dendaPersen / 100;
    }
}