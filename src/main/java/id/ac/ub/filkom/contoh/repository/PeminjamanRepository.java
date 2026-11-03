package id.ac.ub.filkom.contoh.repository;

import id.ac.ub.filkom.contoh.entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
    List<Peminjaman> findAllByOrderByIdPeminjamanDesc();
    List<Peminjaman> findByStatusOrderByIdPeminjamanDesc(String status);
}
