package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "anggota")
public class Anggota {

    @Id
    @Column(name = "id_anggota", length = 15)
    private String idAnggota;

    @Column(name = "nama", length = 100, nullable = false)
    private String nama;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    protected Anggota() { }

    public Anggota(String idAnggota, String nama, String email) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.email = email;
    }

    public String getIdAnggota() { return idAnggota; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }
}
