package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/** Mencatat transaksi peminjaman dan pengembalian buku. */
@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeminjaman;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(name = "tanggal_kembali")
    private LocalDate tanggalKembali;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    protected Peminjaman() { }

    public Peminjaman(Anggota anggota, Buku buku, LocalDate tanggalPinjam) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.status = "DIPINJAM";
    }

    public void kembalikan(LocalDate tanggal) {
        this.tanggalKembali = tanggal;
        this.status = "DIKEMBALIKAN";
    }

    public Long getIdPeminjaman() { return idPeminjaman; }
    public Anggota getAnggota() { return anggota; }
    public Buku getBuku() { return buku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public String getStatus() { return status; }
}
