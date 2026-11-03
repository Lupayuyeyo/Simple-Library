package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.service.AnggotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnggotaController {

    private final AnggotaService anggotaService;

    public AnggotaController(AnggotaService anggotaService) {
        this.anggotaService = anggotaService;
    }

    @GetMapping("/anggota")
    public String daftarAnggota(Model model) {
        model.addAttribute("daftarAnggota", anggotaService.daftarAnggota());
        return "anggota";
    }

    @PostMapping("/anggota")
    public String tambahAnggota(@RequestParam String idAnggota,
                                @RequestParam String nama,
                                @RequestParam String email,
                                Model model) {
        try {
            anggotaService.tambahAnggota(idAnggota, nama, email);
            return "redirect:/anggota";
        } catch (IllegalArgumentException e) {
            model.addAttribute("daftarAnggota", anggotaService.daftarAnggota());
            model.addAttribute("error", e.getMessage());
            return "anggota";
        }
    }
}
