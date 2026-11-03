# Aplikasi Perpustakaan Sederhana (Spring Boot)

Aplikasi ini menyediakan pengelolaan data anggota, data buku, serta transaksi peminjaman dan pengembalian buku.

## Fitur
- Melihat dan menambahkan anggota.
- Melihat, mencari, dan menambahkan buku.
- Mencatat peminjaman dengan memilih anggota dan buku yang sudah terdaftar.
- Stok buku otomatis berkurang ketika dipinjam.
- Mencatat pengembalian melalui transaksi yang masih berstatus DIPINJAM.
- Stok buku otomatis bertambah ketika dikembalikan.
- Menampilkan riwayat transaksi beserta tanggal dan statusnya.

## Menjalankan aplikasi
1. Buka folder proyek yang berisi `pom.xml` melalui IntelliJ IDEA.
2. Pastikan Project SDK menggunakan Java 17.
3. Tunggu Maven menyelesaikan proses sinkronisasi dependency.
4. Jalankan `ContohApplication.java`.
5. Buka `http://localhost:8080` pada browser.

Database menggunakan H2 in-memory. Data akan diinisialisasi ulang ketika aplikasi dimulai ulang karena konfigurasi `create-drop` pada `application.properties`.
