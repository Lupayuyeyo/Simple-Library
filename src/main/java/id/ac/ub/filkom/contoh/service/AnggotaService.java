package id.ac.ub.filkom.contoh.service;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnggotaService {

    private final AnggotaRepository anggotaRepository;

    public AnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    public List<Anggota> daftarAnggota() {
        return anggotaRepository.findAll();
    }

    public Anggota tambahAnggota(String idAnggota, String nama, String email) {
        if (idAnggota == null || idAnggota.isBlank()
                || nama == null || nama.isBlank()
                || email == null || email.isBlank()) {
            throw new IllegalArgumentException("Semua data anggota wajib diisi.");
        }
        idAnggota = idAnggota.trim();
        nama = nama.trim();
        email = email.trim();

        if (anggotaRepository.existsById(idAnggota)) {
            throw new IllegalArgumentException("ID anggota sudah digunakan.");
        }
        if (anggotaRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Email sudah digunakan anggota lain.");
        }
        return anggotaRepository.save(new Anggota(idAnggota, nama, email));
    }
}
