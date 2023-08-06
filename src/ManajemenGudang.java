import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManajemenGudang {
    // Fungsi untuk menambahkan data barang baru ke dalam ArrayList
    public static ArrayList<String> tambahBarang (String kategori, String namaBarang, String stok) {
        ArrayList<String> barang = new ArrayList<>();
        barang.add(kategori);
        barang.add(namaBarang);
        barang.add(stok);
        return barang;
    }

    public static void main() {
        // Inisialisasi struktur data untuk menyimpan informasi gudang
        List<List<List<String>>> gudang = new ArrayList<>();

        // Membuat data sample
        gudang.add(new ArrayList<>());
        gudang.get(0).add(tambahBarang("Pakaian", "Baju Tidur", "15"));
        gudang.get(0).add(tambahBarang("Pakaian", "Jaket Palka", "25"));

        gudang.add(new ArrayList<>());
        gudang.get(1).add(tambahBarang("Makanan", "Nasi Merah", "20"));
        gudang.get(1).add(tambahBarang("Makanan", "Donat Aceh", "50"));

        // Menampilkan pesan selamat datang
        System.out.println("Selamat Datang di Manajemen Gudang Tharissa!");

        // Looping menu utama
        boolean loopingMenu = true;
        Scanner scanner = new Scanner(System.in);
        while (loopingMenu) {
            // Menampilkan menu pilihan kepada pengguna
            tampilkanMenu();
            int pilihanUser = getPilihanUser(scanner);

            switch (pilihanUser) {
                // Menggunakan switch-case untuk menangani pilihan pengguna
                case 1:
                    tambahBarangBaru(scanner, gudang);
                    break;
                case 2:
                    penguranganStokBarang(scanner, gudang);
                    break;
                case 3:
                    penambahanStokBarang(scanner, gudang);
                    break;
                case 4:
                    lihatStokBarang(gudang);
                    break;
                // Keluar dari program
                case 0:
                    System.out.println("Anda memilih untuk keluar dari program.");
                    System.out.println("Terimakasih atas waktunya.");
                    System.out.println("=====================================================================================");
                    loopingMenu = false;
                    break;
                // Menampilkan pesan kesalahan untuk input yang tidak valid semisalnya huruf/simbol
                case -1:
                    System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                    scanner.nextLine();
                    break;
                // Menampilkan pesan kesalahan untuk pilihan yang tidak valid misal angka 5 keatas
                default:
                    System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                    scanner.nextLine();
                    break;
            }
        }
        scanner.close();
    }

    // Fungsi untuk menampilkan menu kepada pengguna
    private static void tampilkanMenu() {
        // Menampilkan menu pilihan yang tersedia
        System.out.println("=====================================================================================");
        System.out.println("Berikut adalah beberapa menu tindakan yang tersedia, silahkan pilih sesuai nomor.");
        System.out.println("1. Tambah data barang baru");
        System.out.println("2. Mengurangi stok barang");
        System.out.println("3. Menambahkan stok barang");
        System.out.println("4. Melihat stok barang yang tersedia");
        System.out.println("0. Keluar dari aplikasi");
        System.out.println("=====================================================================================");
        System.out.print("Pilihan Anda (0-4) : ");
    }

    // Fungsi untuk mendapatkan pilihan user
    private static int getPilihanUser(Scanner scanner) {
        // Mengambil input pilihan user dan mengembalikan nilainya jika benar/terjadi kesalahan input data
        int pilihanUser;
        if (scanner.hasNextInt()) {
            pilihanUser = scanner.nextInt();
            scanner.nextLine(); // agar tidak terjadi stuck loop
        } else {
            scanner.nextLine(); // agar tidak terjadi stuck loop
            pilihanUser = -1; // set untuk nilai yang salah
        }
        return pilihanUser;
    }

    // Fungsi untuk menambahkan barang baru ke dalam gudang
    private static void tambahBarangBaru(Scanner scanner, List<List<List<String>>> gudang) {
        // Logika untuk menambahkan barang baru ke dalam gudang
        System.out.println("Anda memilih menu untuk menambahkan barang baru.");
        System.out.print("Silahkan inputkan kategori barang yang diinginkan : ");
        String kategoriBaru = scanner.nextLine();

        // Cek kategori ada atau tidak
        int categoryIndex = -1;
        boolean categoryExists = false;
        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
            String namaKategori = gudang.get(indeksKategori).get(0).get(0);
            if (namaKategori.equalsIgnoreCase(kategoriBaru)) {
                categoryExists = true;
                categoryIndex = indeksKategori;
                break;
            }
        }

        System.out.print("Silahkan inputkan nama barang yang diinginkan : ");
        String tambahNamaBarang = scanner.nextLine();
        System.out.print("Silahkan inputkan stok barang yang diinginkan : ");
        String addStokBarang = scanner.nextLine();
        if (addStokBarang.matches("^[0-9]+$")) {
            if (!categoryExists) {
                gudang.add(new ArrayList<>());
                categoryIndex = gudang.size() - 1;
            }
            int tambahStokBarang = Integer.parseInt(addStokBarang);
            if (tambahStokBarang > 0) {
                // Cek bila barang sudah ada
                boolean itemExists = false;
                for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                    for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                        String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                        if (barangName.equalsIgnoreCase(tambahNamaBarang)) {
                            itemExists = true;
                            break;
                        }
                    }
                }
                if (!itemExists) {
                    gudang.get(categoryIndex).add(tambahBarang(kategoriBaru, tambahNamaBarang, String.valueOf(tambahStokBarang)));
                    System.out.println("=====================================================================================");
                    System.out.println("Selamat! Anda berhasil menambahkan data barang baru! Berikut update datanya : ");
                    for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                        String namaKategori = gudang.get(indeksKategori).get(0).get(0);
                        System.out.printf("\nKategori : %s - %d\n", namaKategori, indeksKategori);
                        System.out.println("Nama Barang\t\t\t\t\t\t\tStok");
                        for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                            String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                            String stok = gudang.get(indeksKategori).get(indeksBarang).get(2);
                            System.out.printf("%s - %d\t\t\t\t\t\t%s\n", barangName, indeksBarang, stok);
                        }
                    }
                } else {
                    System.out.println("Mohon maaf, barang tersebut sudah ada. Silahkan lihat stok barang yang tersedia.");
                }
            } else {
                System.out.println("Maaf! Input stok harus berupa angka dan merupakan bilangan positif! Untuk kembali ke menu, silahkan tekan enter.");
                scanner.nextLine();
            }
        } else {
            System.out.println("Maaf! Input stok harus berupa angka dan merupakan bilangan positif! Untuk kembali ke menu, silahkan tekan enter.");
            scanner.nextLine();
        }
    }

    // Fungsi untuk mengurangi stok barang
    private static void penguranganStokBarang(Scanner scanner, List<List<List<String>>> gudang) {
        // Logika untuk mengurangi stok barang dalam gudang
        System.out.println("Anda memilih menu untuk mengurangi stok barang.");
        System.out.print("Silahkan inputkan kategori barang yang ingin dirubah : ");
        String kategoriBaru = scanner.nextLine();

        // Mengecek ada tidaknya kategori sesuai dengan inputan user
        boolean categoryExists = false;
        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                String namaKategori = gudang.get(indeksKategori).get(0).get(0);
                if (namaKategori.equalsIgnoreCase(kategoriBaru)) {
                    categoryExists = true;
                    break;
                }
        }
        if (categoryExists) {
            System.out.print("Silahkan inputkan nama barang yang diinginkan : ");
            String tambahNamaBarang = scanner.nextLine();

            // Cek bila barang sudah ada
            boolean itemExists = false;
            for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                    String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                    if (barangName.equalsIgnoreCase(tambahNamaBarang)) {
                        itemExists = true;
                        break;
                    }
                }
            }

            if (itemExists) {
                System.out.print("Silahkan inputkan jumlah stok yang ingin dikurangi : ");
                String kurangiStokBarang = scanner.nextLine();
                if (kurangiStokBarang.matches("^[0-9]+$")) {
                    int minusStock = Integer.parseInt(kurangiStokBarang);
                    if ((minusStock > 0)) {
                        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                            for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                                String namaKategori = gudang.get(indeksKategori).get(indeksBarang).get(0);
                                String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                                String stok = gudang.get(indeksKategori).get(indeksBarang).get(2);
                                if (barangName.equalsIgnoreCase(tambahNamaBarang)) {
                                    int currentStock = Integer.parseInt(stok);
                                    int updatedStock = currentStock - minusStock;
                                    if (updatedStock > 0) {
                                        gudang.get(indeksKategori).get(indeksBarang).set(2, String.valueOf(updatedStock));
                                        System.out.println("=====================================================================================");
                                        System.out.println("Selamat! Anda berhasil mengurangi stok barang! Berikut update datanya : ");
                                        System.out.println("Kategori\t\t\t\tNama Barang\t\t\t\t\t\t\tStok");
                                        System.out.printf("%s - %d\t\t\t\t%s - %d\t\t\t\t\t\t%s\n", namaKategori, indeksKategori, barangName, indeksBarang, updatedStock);
                                        break;
                                    } else {
                                        System.out.println("Maaf, barang tidak bisa dikurangkan dengan " + minusStock + " karena menghasilkan nilai yang tidak valid.");
                                    }
                                }

                            }
                        }
                    } else {
                        System.out.println("Maaf! Input stok harus berupa angka dan merupakan bilangan positif! Untuk kembali ke menu, silahkan tekan enter.");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Maaf! Input stok harus berupa angka dan merupakan bilangan positif! Untuk kembali ke menu, silahkan tekan enter.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Maaf! Barang tidak ada, silahkan buat baru.");
            }
        } else {
            System.out.println("Maaf! Kategori tidak ada, silahkan buat baru.");
        }
    }

    // Fungsi untuk menambahkan stok barang
    private static void penambahanStokBarang(Scanner scanner, List<List<List<String>>> gudang) {
        // Logika untuk menambahkan stok barang dalam gudang
        System.out.println("Anda memilih menu untuk menambahkan stok barang.");
        System.out.print("Silahkan inputkan kategori barang yang ingin dirubah : ");
        String kategoriBaru = scanner.nextLine();

        boolean categoryExists = false;
        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
            String namaKategori = gudang.get(indeksKategori).get(0).get(0);
            if (namaKategori.equalsIgnoreCase(kategoriBaru)) {
                categoryExists = true;
                break;
            }
        }

        if (categoryExists) {
            System.out.print("Silahkan inputkan nama barang yang diinginkan : ");
            String tambahNamaBarang = scanner.nextLine();

            // Cek bila barang sudah ada
            boolean itemExists = false;
            for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                    String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                    if (barangName.equalsIgnoreCase(tambahNamaBarang)) {
                        itemExists = true;
                        break;
                    }
                }
            }

            if (itemExists) {
                System.out.print("Silahkan inputkan jumlah stok yang ingin ditambah : ");
                String tambahStok = scanner.nextLine();
                if (tambahStok.matches("^[0-9]+$")) {
                    int additionalStock = Integer.parseInt(tambahStok);
                    if ((additionalStock > 0)) {
                        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                            for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                                String namaKategori = gudang.get(indeksKategori).get(indeksBarang).get(0);
                                String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                                String stok = gudang.get(indeksKategori).get(indeksBarang).get(2);
                                if (barangName.equalsIgnoreCase(tambahNamaBarang)) {
                                    int currentStock = Integer.parseInt(stok);
                                    String updatedStock = String.valueOf(currentStock + additionalStock);
                                    gudang.get(indeksKategori).get(indeksBarang).set(2, updatedStock);
                                    System.out.println("=====================================================================================");
                                    System.out.println("Selamat! Anda berhasil menambahkan stok barang! Berikut update datanya : ");
                                    System.out.println("Kategori\t\t\t\tNama Barang\t\t\t\t\t\t\tStok");
                                    System.out.printf("%s - %d\t\t\t\t%s - %d\t\t\t\t\t\t%s\n", namaKategori, indeksKategori, barangName, indeksBarang, updatedStock);
                                    break;
                                }

                            }
                        }
                    } else {
                        System.out.println("Maaf! Input stok harus berupa angka dan merupakan bilangan positif! Untuk kembali ke menu, silahkan tekan enter.");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Maaf! Input stok harus berupa angka dan merupakan bilangan positif! Untuk kembali ke menu, silahkan tekan enter.");
                    scanner.nextLine();
                }

            } else {
                System.out.println("Maaf! Barang tidak ada, silahkan buat baru.");
            }
        } else {
            System.out.println("Maaf! Kategori tidak ada, silahkan buat baru.");
        }

    }

    // Fungsi untuk melihat stok barang yang tersedia
    private static void lihatStokBarang(List<List<List<String>>> gudang) {
        // Menampilkan stok barang yang ada dalam gudang
        System.out.println("Anda memilih menu untuk melihat barang yang tersedia.");
        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
            String namaKategori = gudang.get(indeksKategori).get(0).get(0);
            System.out.printf("\nKategori : %s - %d\n", namaKategori, indeksKategori);
            System.out.println("Nama Barang\t\t\t\t\t\t\tStok");
            for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                String stok = gudang.get(indeksKategori).get(indeksBarang).get(2);
                System.out.printf("%s - %d\t\t\t\t\t\t%s\n", barangName, indeksBarang, stok);
            }
        }
    }
}