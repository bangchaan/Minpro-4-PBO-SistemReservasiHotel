package model.DAO;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import model.Reservasi;
import model.ReservasiVIP;
import model.ReservasiStandard;

public class ReservasiDAO {

    // CREATE
    public boolean tambahReservasi(Reservasi reservasi) {
        String sql = "INSERT INTO reservasi (tipe, nama_tamu, jumlah_malam, harga_per_malam, status, "
                + "denda_persen, jenis_penyambutan, biaya_penyambutan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = Koneksi.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, reservasi.getTipe());   
            pstmt.setString(2, reservasi.getNamaTamu());
            pstmt.setInt(3, reservasi.getJumlahMalam());
            pstmt.setInt(4, reservasi.getHargaPerMalam());
            pstmt.setString(5, reservasi.getStatus());
            isiDataKhusus(pstmt, reservasi, 6);        

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    reservasi.setIdReservasi(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menambah reservasi - " + e.getMessage());
            return false;
        }
    }

    // READ (by ID)
    public Reservasi cariReservasiById(int id) {
        String sql = "SELECT * FROM reservasi WHERE id_reservasi = ?";
        Reservasi reservasi = null;
        Connection conn = Koneksi.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    reservasi = buatObjek(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari reservasi - " + e.getMessage());
        }
        return reservasi;
    }

    // READ (all)
    public ArrayList<Reservasi> getAllReservasi() {
        ArrayList<Reservasi> daftar = new ArrayList<>();
        String sql = "SELECT * FROM reservasi ORDER BY id_reservasi";
        Connection conn = Koneksi.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                daftar.add(buatObjek(rs));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data reservasi - " + e.getMessage());
        }
        return daftar;
    }

    // UPDATE
    public boolean updateReservasi(Reservasi reservasi) {
        String sql = "UPDATE reservasi SET nama_tamu=?, jumlah_malam=?, status=?, "
                + "denda_persen=?, jenis_penyambutan=?, biaya_penyambutan=? WHERE id_reservasi=?";
        Connection conn = Koneksi.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservasi.getNamaTamu());
            pstmt.setInt(2, reservasi.getJumlahMalam());
            pstmt.setString(3, reservasi.getStatus());
            isiDataKhusus(pstmt, reservasi, 4);        // parameter 4, 5, 6
            pstmt.setInt(7, reservasi.getIdReservasi());

            int barisBerubah = pstmt.executeUpdate();
            return barisBerubah > 0;
        } catch (SQLException e) {
            System.out.println("Gagal mengubah reservasi - " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean hapusReservasi(int id) {
        String sql = "DELETE FROM reservasi WHERE id_reservasi=?";
        Connection conn = Koneksi.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int barisBerubah = pstmt.executeUpdate();
            return barisBerubah > 0;
        } catch (SQLException e) {
            System.out.println("Gagal menghapus reservasi - " + e.getMessage());
            return false;
        }
    }

    private Reservasi buatObjek(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_reservasi");
        String tipe = rs.getString("tipe");
        String nama = rs.getString("nama_tamu");
        int malam = rs.getInt("jumlah_malam");

        Reservasi reservasi;

        if ("VIP".equals(tipe)) {
            reservasi = new ReservasiVIP(id, nama, malam, rs.getString("jenis_penyambutan"));
        } else {
            ReservasiStandard standard = new ReservasiStandard(id, nama, malam);
            standard.setDendaPersen(rs.getInt("denda_persen"));
            reservasi = standard;
        }

        reservasi.setHargaPerMalam(rs.getInt("harga_per_malam"));
        reservasi.setStatus(rs.getString("status"));
        return reservasi;
    }


    private void isiDataKhusus(PreparedStatement pstmt, Reservasi reservasi, int indeks)
            throws SQLException {
        if (reservasi instanceof ReservasiStandard) {
            ReservasiStandard standard = (ReservasiStandard) reservasi;
            pstmt.setInt(indeks, standard.getDendaPersen());
            pstmt.setNull(indeks + 1, Types.VARCHAR);
            pstmt.setNull(indeks + 2, Types.INTEGER);
        } else if (reservasi instanceof ReservasiVIP) {
            ReservasiVIP vip = (ReservasiVIP) reservasi;
            pstmt.setNull(indeks, Types.INTEGER);
            pstmt.setString(indeks + 1, vip.getJenisPenyambutan());
            pstmt.setInt(indeks + 2, vip.getBiayaPenyambutan());
        }
    }
}