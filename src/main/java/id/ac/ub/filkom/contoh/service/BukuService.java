package id.ac.ub.filkom.contoh.service;

import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.repository.BukuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BukuService {

    private final BukuRepository bukuRepository;

    public BukuService(BukuRepository bukuRepository) {
        this.bukuRepository = bukuRepository;
    }

    public List<Buku> cariBuku(String kata) {
        if (kata == null || kata.isBlank()) {
            return bukuRepository.findAll();
        }
        return bukuRepository.findByJudulContainingIgnoreCase(kata.trim());
    }

    public Buku tambahBuku(String idBuku, String judul, int stok) {
        if (idBuku == null || idBuku.isBlank() || judul == null || judul.isBlank()) {
            throw new IllegalArgumentException("ID dan judul buku wajib diisi.");
        }
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif.");
        }
        idBuku = idBuku.trim();
        judul = judul.trim();
        if (bukuRepository.existsById(idBuku)) {
            throw new IllegalArgumentException("ID buku sudah digunakan.");
        }
        return bukuRepository.save(new Buku(idBuku, judul, stok));
    }
}
