package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.service.BukuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BukuController {

    private final BukuService bukuService;

    public BukuController(BukuService bukuService) {
        this.bukuService = bukuService;
    }

    @GetMapping("/")
    public String beranda() {
        return "redirect:/buku";
    }

    @GetMapping("/buku")
    public String daftarBuku(@RequestParam(required = false) String kata, Model model) {
        model.addAttribute("daftarBuku", bukuService.cariBuku(kata));
        model.addAttribute("kata", kata);
        return "buku";
    }

    @PostMapping("/buku")
    public String tambahBuku(@RequestParam String idBuku,
                             @RequestParam String judul,
                             @RequestParam int stok,
                             @RequestParam(required = false) String kata,
                             Model model) {
        try {
            bukuService.tambahBuku(idBuku, judul, stok);
            return "redirect:/buku";
        } catch (IllegalArgumentException e) {
            model.addAttribute("daftarBuku", bukuService.cariBuku(kata));
            model.addAttribute("kata", kata);
            model.addAttribute("error", e.getMessage());
            return "buku";
        }
    }
}
