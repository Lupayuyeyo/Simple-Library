package id.ac.ub.filkom.contoh.service;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.Peminjaman;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import id.ac.ub.filkom.contoh.repository.BukuRepository;
import id.ac.ub.filkom.contoh.repository.PeminjamanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeminjamanService {
    private final PeminjamanRepository peminjamanRepository;
    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;

    public PeminjamanService(PeminjamanRepository peminjamanRepository,
                             AnggotaRepository anggotaRepository,
                             BukuRepository bukuRepository) {
        this.peminjamanRepository = peminjamanRepository;
        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
    }

    public List<Peminjaman> daftarPeminjaman() {
        return peminjamanRepository.findAllByOrderByIdPeminjamanDesc();
    }

    public List<Peminjaman> daftarYangBelumKembali() {
        return peminjamanRepository.findByStatusOrderByIdPeminjamanDesc("DIPINJAM");
    }

    @Transactional
    public void pinjam(String idAnggota, String idBuku) {
        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> new IllegalArgumentException("Anggota tidak ditemukan."));
        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() -> new IllegalArgumentException("Buku tidak ditemukan."));

        if (buku.getStok() <= 0) {
            throw new IllegalArgumentException("Stok buku sedang habis.");
        }

        buku.setStok(buku.getStok() - 1);
        bukuRepository.save(buku);
        peminjamanRepository.save(new Peminjaman(anggota, buku, LocalDate.now()));
    }

    @Transactional
    public void kembalikan(Long idPeminjaman) {
        Peminjaman peminjaman = peminjamanRepository.findById(idPeminjaman)
                .orElseThrow(() -> new IllegalArgumentException("Data peminjaman tidak ditemukan."));

        if (!"DIPINJAM".equals(peminjaman.getStatus())) {
            throw new IllegalArgumentException("Buku pada transaksi ini sudah dikembalikan.");
        }

        peminjaman.kembalikan(LocalDate.now());
        Buku buku = peminjaman.getBuku();
        buku.setStok(buku.getStok() + 1);
        bukuRepository.save(buku);
        peminjamanRepository.save(peminjaman);
    }
}
