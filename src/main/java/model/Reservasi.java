package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Table(name = "reservasi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipe", discriminatorType = DiscriminatorType.STRING)
public abstract class Reservasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservasi")
    private int idReservasi;

    @Column(name = "nama_tamu")
    private String namaTamu;

    @Column(name = "jumlah_malam")
    private int jumlahMalam;

    @Column(name = "harga_per_malam")
    private int hargaPerMalam;

    @Column(name = "status")
    private String status;

public static final String MENUNGGU = "Menunggu";
public static final String CHECK_IN = "Check-In";
public static final String CHECK_OUT = "Check-Out";

public static final int MAKS_NAMA = 25;

    protected Reservasi() {
    }

    public Reservasi(int idReservasi, String namaTamu,
                     int jumlahMalam, int hargaPerMalam) {
        this.idReservasi = idReservasi;
        this.namaTamu = namaTamu;
        this.jumlahMalam = jumlahMalam;
        this.hargaPerMalam = hargaPerMalam;
        this.status = MENUNGGU;
    }

    public int getIdReservasi() {
        return idReservasi;
    }

    public void setIdReservasi(int idReservasi) {
        this.idReservasi = idReservasi;
    }

    public String getNamaTamu() {
        return namaTamu;
    }

    public void setNamaTamu(String namaTamu) {
        this.namaTamu = namaTamu;
    }

    public int getJumlahMalam() {
        return jumlahMalam;
    }

    public void setJumlahMalam(int jumlahMalam) {
        this.jumlahMalam = jumlahMalam;
    }

    public int getHargaPerMalam() {
        return hargaPerMalam;
    }

    public void setHargaPerMalam(int hargaPerMalam) {
        this.hargaPerMalam = hargaPerMalam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int hitungTotalBiaya() {
        return hargaPerMalam * jumlahMalam;
    }

    public abstract String getTipe();

    public abstract String getDetailTambahan();

    public void checkIn() {
        status = CHECK_IN;
    }

    public void checkOut() {
        status = CHECK_OUT;
    }
}