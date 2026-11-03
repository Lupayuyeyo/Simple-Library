package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.service.AnggotaService;
import id.ac.ub.filkom.contoh.service.BukuService;
import id.ac.ub.filkom.contoh.service.PeminjamanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PeminjamanController {
    private final PeminjamanService peminjamanService;
    private final AnggotaService anggotaService;
    private final BukuService bukuService;

    public PeminjamanController(PeminjamanService peminjamanService,
                                AnggotaService anggotaService,
                                BukuService bukuService) {
        this.peminjamanService = peminjamanService;
        this.anggotaService = anggotaService;
        this.bukuService = bukuService;
    }

    @GetMapping("/peminjaman")
    public String halamanPeminjaman(Model model) {
        model.addAttribute("daftarPeminjaman", peminjamanService.daftarPeminjaman());
        model.addAttribute("peminjamanAktif", peminjamanService.daftarYangBelumKembali());
        model.addAttribute("daftarAnggota", anggotaService.daftarAnggota());
        model.addAttribute("daftarBuku", bukuService.cariBuku(null));
        return "peminjaman";
    }

    @PostMapping("/peminjaman")
    public String pinjam(@RequestParam String idAnggota,
                         @RequestParam String idBuku,
                         RedirectAttributes redirectAttributes) {
        try {
            peminjamanService.pinjam(idAnggota, idBuku);
            redirectAttributes.addFlashAttribute("pesan", "Peminjaman berhasil dicatat.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/peminjaman";
    }

    @PostMapping("/peminjaman/kembalikan")
    public String kembalikan(@RequestParam Long idPeminjaman,
                             RedirectAttributes redirectAttributes) {
        try {
            peminjamanService.kembalikan(idPeminjaman);
            redirectAttributes.addFlashAttribute("pesan", "Pengembalian buku berhasil dicatat.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/peminjaman";
    }
}
