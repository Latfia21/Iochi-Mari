// File: src/java/Layanan0046.java
// NIM: 1003260046 | Domain: Laundry | Nilai Dasar: 32 -> tarifPerKg = 32000

// 1. CLASS MODEL DATA
public class Layanan0046 {
    // Tiga attribute private (Enkapsulasi: stok/resep dapur dikunci rapat)
    private String namaPelanggan;
    private double beratKg;
    private double tarifPerKg;

    // Constructor berparameter untuk inisialisasi awal saat object dibuat
    public Layanan0046(String namaPelanggan, double beratKg, double tarifPerKg) {
        this.namaPelanggan = namaPelanggan;
        this.beratKg = beratKg;
        this.tarifPerKg = tarifPerKg;
    }

    // Getter dan Setter dengan validasi ketat (Pelayan penjaga aturan dapur)
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        if (namaPelanggan == null || namaPelanggan.trim().isEmpty()) {
            System.out.println("[Validasi Gagal] Nama pelanggan tidak boleh kosong!");
        } else {
            this.namaPelanggan = namaPelanggan;
        }
    }

    public double getBeratKg() {
        return beratKg;
    }

    public void setBeratKg(double beratKg) {
        if (beratKg <= 0) {
            System.out.println("[Validasi Gagal] Berat cucian harus lebih dari 0 kg!");
        } else {
            this.beratKg = beratKg;
        }
    }

    public double getTarifPerKg() {
        return tarifPerKg;
    }

    public void setTarifPerKg(double tarifPerKg) {
        if (tarifPerKg < 10000) {
            System.out.println("[Validasi Gagal] Tarif per kg tidak valid (minimal Rp10.000)!");
        } else {
            this.tarifPerKg = tarifPerKg;
        }
    }

    // Method perhitungan bisnis: menghitung total harga laundry
    public double hitungTotalBiaya() {
        return this.beratKg * this.tarifPerKg;
    }

    // Method utama untuk mengeksekusi program
    public static void main(String[] args) {
        // Membuat object pengelola dari class Pesanan
        Pesanan pengelola = new Pesanan("Laundry Express 0046", "Shift Pagi");

        // Membuat Object 1 dan Object 2 dari class model Layanan0046 dengan data berbeda
        // Nilai tarifPerKg diisi 32000 sesuai aturan NIM (32 x 1000)
        Layanan0046 pelanggan1 = new Layanan0046("Budi Santoso", 4.5, 32000);
        Layanan0046 pelanggan2 = new Layanan0046("Siti Aminah", 7.0, 32000);

        // Menampilkan hasil kedua object melalui class pengelola
        pengelola.tampilkanHeader();
        pengelola.cetakNota(pelanggan1, 1);
        pengelola.cetakNota(pelanggan2, 2);

        // Demonstrasi uji validasi Setter (mencoba memasukkan nilai minus)
        System.out.println("\n--- Uji Validasi Keamanan Setter ---");
        pelanggan1.setBeratKg(-3.0); // Akan ditolak oleh validasi setter
        System.out.println("Berat cucian " + pelanggan1.getNamaPelanggan() + " tetap aman: " + pelanggan1.getBeratKg() + " kg");

        // Menampilkan total omzet gabungan
        double totalOmzet = pengelola.hitungTotalOmzet(pelanggan1, pelanggan2);
        System.out.println("==========================================");
        System.out.println("Total Omzet Cabang : Rp" + String.format("%,.0f", totalOmzet));
        System.out.println("==========================================");
    }
}

// 2. CLASS PENGELOLA (MANAGER CLASS)
class Pesanan {
    // Attribute private milik class pengelola
    private String namaCabang;
    private String shiftKasir;

    // Constructor berparameter pada class pengelola
    public Pesanan(String namaCabang, String shiftKasir) {
        this.namaCabang = namaCabang;
        this.shiftKasir = shiftKasir;
    }

    // Getter dan Setter dengan validasi untuk class pengelola
    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        if (namaCabang != null && !namaCabang.trim().isEmpty()) {
            this.namaCabang = namaCabang;
        }
    }

    public String getShiftKasir() {
        return shiftKasir;
    }

    public void setShiftKasir(String shiftKasir) {
        if (shiftKasir != null && !shiftKasir.trim().isEmpty()) {
            this.shiftKasir = shiftKasir;
        }
    }

    // Method interaksi dengan object Layanan0046
    public void tampilkanHeader() {
        System.out.println("==========================================");
        System.out.println("Cabang : " + this.namaCabang + " (" + this.shiftKasir + ")");
        System.out.println("==========================================");
    }

    public void cetakNota(Layanan0046 layanan, int nomorAntrean) {
        System.out.println("Nota #" + nomorAntrean);
        System.out.println("Nama Pelanggan : " + layanan.getNamaPelanggan());
        System.out.println("Berat Cucian   : " + layanan.getBeratKg() + " kg");
        System.out.println("Tarif per Kg   : Rp" + String.format("%,.0f", layanan.getTarifPerKg()));
        System.out.println("Total Biaya    : Rp" + String.format("%,.0f", layanan.hitungTotalBiaya()));
        System.out.println("------------------------------------------");
    }

    // Method perhitungan agregasi dua object layanan
    public double hitungTotalOmzet(Layanan0046 l1, Layanan0046 l2) {
        return l1.hitungTotalBiaya() + l2.hitungTotalBiaya();
    }
}
