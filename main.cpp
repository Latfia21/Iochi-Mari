// File: src/cpp/main.cpp
// NIM: 1003260046 | Domain: Laundry | Nilai Dasar: 32 -> tarifPerKg = 32000

#include <iostream>
#include <string>
#include <iomanip>

// 1. CLASS MODEL DATA
class Layanan0046 {
private:
    std::string namaPelanggan;
    double beratKg;
    double tarifPerKg;

public:
    // Constructor berparameter dengan initializer list
    Layanan0046(std::string namaPelanggan, double beratKg, double tarifPerKg)
        : namaPelanggan(namaPelanggan), beratKg(beratKg), tarifPerKg(tarifPerKg) {}

    // Getter dan Setter dengan validasi
    std::string getNamaPelanggan() const {
        return namaPelanggan;
    }

    void setNamaPelanggan(const std::string& namaBaru) {
        if (namaBaru.empty()) {
            std::cout << "[Validasi Gagal] Nama pelanggan tidak boleh kosong!\n";
        } else {
            namaPelanggan = namaBaru;
        }
    }

    double getBeratKg() const {
        return beratKg;
    }

    void setBeratKg(double beratBaru) {
        if (beratBaru <= 0) {
            std::cout << "[Validasi Gagal] Berat cucian harus lebih dari 0 kg!\n";
        } else {
            beratKg = beratBaru;
        }
    }

    double getTarifPerKg() const {
        return tarifPerKg;
    }

    void setTarifPerKg(double tarifBaru) {
        if (tarifBaru < 10000) {
            std::cout << "[Validasi Gagal] Tarif per kg tidak valid (minimal Rp10.000)!\n";
        } else {
            tarifPerKg = tarifBaru;
        }
    }

    // Method perhitungan total biaya laundry
    double hitungTotalBiaya() const {
        return beratKg * tarifPerKg;
    }
};

// 2. CLASS PENGELOLA (MANAGER CLASS)
class Pesanan {
private:
    std::string namaCabang;
    std::string shiftKasir;

public:
    // Constructor berparameter
    Pesanan(std::string namaCabang, std::string shiftKasir)
        : namaCabang(namaCabang), shiftKasir(shiftKasir) {}

    std::string getNamaCabang() const {
        return namaCabang;
    }

    void setNamaCabang(const std::string& cabangBaru) {
        if (!cabangBaru.empty()) {
            namaCabang = cabangBaru;
        }
    }

    std::string getShiftKasir() const {
        return shiftKasir;
    }

    void setShiftKasir(const std::string& shiftBaru) {
        if (!shiftBaru.empty()) {
            shiftKasir = shiftBaru;
        }
    }

    void tampilkanHeader() const {
        std::cout << "==========================================\n";
        std::cout << "Cabang : " << namaCabang << " (" << shiftKasir << ")\n";
        std::cout << "==========================================\n";
    }

    void cetakNota(const Layanan0046& layanan, int nomorAntrean) const {
        std::cout << std::fixed << std::setprecision(0);
        std::cout << "Nota #" << nomorAntrean << "\n";
        std::cout << "Nama Pelanggan : " << layanan.getNamaPelanggan() << "\n";
        std::cout << std::setprecision(1);
        std::cout << "Berat Cucian   : " << layanan.getBeratKg() << " kg\n";
        std::cout << std::setprecision(0);
        std::cout << "Tarif per Kg   : Rp" << layanan.getTarifPerKg() << "\n";
        std::cout << "Total Biaya    : Rp" << layanan.hitungTotalBiaya() << "\n";
        std::cout << "------------------------------------------\n";
    }

    double hitungTotalOmzet(const Layanan0046& l1, const Layanan0046& l2) const {
        return l1.hitungTotalBiaya() + l2.hitungTotalBiaya();
    }
};

int main() {
    // Membuat object pengelola
    Pesanan pengelola("Laundry Express 0046", "Shift Pagi");

    // Membuat 2 object dari class model Layanan0046 dengan data berbeda
    Layanan0046 pelanggan1("Budi Santoso", 4.5, 32000);
    Layanan0046 pelanggan2("Siti Aminah", 7.0, 32000);

    // Menampilkan data transaksi
    pengelola.tampilkanHeader();
    pengelola.cetakNota(pelanggan1, 1);
    pengelola.cetakNota(pelanggan2, 2);

    // Uji validasi setter
    std::cout << "\n--- Uji Validasi Keamanan Setter ---\n";
    pelanggan1.setBeratKg(-3.0);
    std::cout << std::fixed << std::setprecision(1);
    std::cout << "Berat cucian " << pelanggan1.getNamaPelanggan()
              << " tetap aman: " << pelanggan1.getBeratKg() << " kg\n";

    // Menampilkan total omzet
    double totalOmzet = pengelola.hitungTotalOmzet(pelanggan1, pelanggan2);
    std::cout << std::setprecision(0);
    std::cout << "==========================================\n";
    std::cout << "Total Omzet Cabang : Rp" << totalOmzet << "\n";
    std::cout << "==========================================\n";

    return 0;
}