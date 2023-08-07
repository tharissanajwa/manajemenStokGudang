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
            // Menampilkan input pilihan menu kepada pengguna dengan memanggil fungsi yang sudah dibuat
            int pilihanUser = getPilihanUser(scanner);

            switch (pilihanUser) {
                // Menggunakan switch-case untuk menangani pilihan pengguna
                case 1:
                    tambahBarangBaru(scanner, gudang); // Memanggil fungsi tambah barang baru dengan menyertakan scanner dan arrayList nya(data set)
                    break;
                case 2:
                    penguranganStokBarang(scanner, gudang); // Memanggil fungsi pengurangan stok
                    break;
                case 3:
                    penambahanStokBarang(scanner, gudang); // Memanggil fungsi penambahan stok
                    break;
                case 4:
                    lihatStokBarang(gudang); // Memanggil fungsi lihat stok barang
                    break;
                // Keluar dari program
                case 0:
                    System.out.println("Anda memilih untuk keluar dari program.");
                    System.out.println("Terimakasih atas waktunya.");
                    System.out.println("=====================================================================================");
                    loopingMenu = false; // Loop diberhentikan karena program telah selesai
                    break;
                // Menampilkan pesan kesalahan untuk input yang tidak valid semisalnya huruf/simbol
                case -1:
                    System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                    scanner.nextLine(); // Fungsinya untuk menghapus buffer
                    break;
                // Menampilkan pesan kesalahan untuk pilihan yang tidak valid misal angka 5 keatas
                default:
                    System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                    scanner.nextLine();
                    break;
            }
        }
        scanner.close(); // Fungsinya untuk menghindari kebocoran sumber daya
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
        // Mengecek inputan pengguna apakah integer atau bukan
        if (scanner.hasNextInt()) {
            pilihanUser = scanner.nextInt();
            scanner.nextLine(); // Untuk membersihkan buffer
        } else {
            scanner.nextLine(); // Untuk membersihkan buffer
            pilihanUser = -1; // Set untuk nilai yang salah
        }
        return pilihanUser;
    }

    // Fungsi untuk menambahkan barang baru ke dalam gudang
    private static void tambahBarangBaru(Scanner scanner, List<List<List<String>>> gudang) {
        // Logika untuk menambahkan barang baru ke dalam gudang
        System.out.println("Anda memilih menu untuk menambahkan barang baru.");
        System.out.print("Silahkan inputkan kategori barang yang diinginkan : ");
        String kategoriBaru = scanner.nextLine();

        // Cek kategori sudah ada atau belum
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
        // Cek agar inputan stok hanya berupa angka 0-9
        if (addStokBarang.matches("^[0-9]+$")) {
            // Fungsinya untuk bila kategori tidak ada, maka dibuatkan kategori baru
            if (!categoryExists) {
                gudang.add(new ArrayList<>());
                categoryIndex = gudang.size() - 1;
            }
            // Karena sebelumnya input stok berupa string, maka untuk melakukan rumusnya harus dikonversi ke integer terlebih dahulu
            int tambahStokBarang = Integer.parseInt(addStokBarang);
            // Pengecekan agar stok tidak boleh diinputkan kurang dari 0
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
                // Bila barang sudah ada, maka datanya tidak akan ditambahkan ke data set
                if (!itemExists) {
                    // Menambahkan barang baru pada data set
                    gudang.get(categoryIndex).add(tambahBarang(kategoriBaru, tambahNamaBarang, String.valueOf(tambahStokBarang)));
                    System.out.println("=====================================================================================");
                    System.out.println("Selamat! Anda berhasil menambahkan data barang baru! Berikut update datanya : ");
                    // Looping 2 kali agar menampilkan kategori, barang, dan stok secara keseluruhan
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
                    // Menampilkan pesan kesalahan jika barang sudah ada
                    System.out.println("Mohon maaf, barang tersebut sudah ada. Silahkan lihat stok barang yang tersedia.");
                }
            } else {
                // Menampilkan pesan kesalahan jika input stok kurang dari 0 dan bukan merupakan angka
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
        // Jika kategori ada, maka program akan dilanjutkan
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

            // Jika barang sudah ada, maka program akan dilanjutkan
            if (itemExists) {
                System.out.print("Silahkan inputkan jumlah stok yang ingin dikurangi : ");
                String kurangiStokBarang = scanner.nextLine();
                // Input kurangi stok harus berupa angka
                if (kurangiStokBarang.matches("^[0-9]+$")) {
                    int minusStock = Integer.parseInt(kurangiStokBarang);
                    // Input kurangi stok harus lebih dari 0
                    if ((minusStock > 0)) {
                        // Looping data 2 kali untuk mengambil data barang sesuai dengan inputan kategori dan nama barang oleh pengguna
                        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
                            for (int indeksBarang = 0; indeksBarang < gudang.get(indeksKategori).size(); indeksBarang++) {
                                String namaKategori = gudang.get(indeksKategori).get(indeksBarang).get(0);
                                String barangName = gudang.get(indeksKategori).get(indeksBarang).get(1);
                                String stok = gudang.get(indeksKategori).get(indeksBarang).get(2);
                                if (barangName.equalsIgnoreCase(tambahNamaBarang)) {
                                    int currentStock = Integer.parseInt(stok);
                                    int updatedStock = currentStock - minusStock;
                                    // Fungsinya untuk mengecek apakah nilai yang dihasilkan lebih dari 0 atau tidak
                                    if (updatedStock > 0) {
                                        // Fungsinya untuk mengubah data yang sudah ada agar terupdate
                                        gudang.get(indeksKategori).get(indeksBarang).set(2, String.valueOf(updatedStock));
                                        // Menampilkan update datanya
                                        System.out.println("=====================================================================================");
                                        System.out.println("Selamat! Anda berhasil mengurangi stok barang! Berikut update datanya : ");
                                        System.out.println("Kategori\t\t\t\tNama Barang\t\t\t\t\t\t\tStok");
                                        System.out.printf("%s - %d\t\t\t\t%s - %d\t\t\t\t\t\t%s\n", namaKategori, indeksKategori, barangName, indeksBarang, updatedStock);
                                        break;
                                    } else {
                                        // Menampilkan pesan kesalahan bila hasilnya kurang dari 0
                                        System.out.println("Maaf, barang tidak bisa dikurangkan dengan " + minusStock + " karena menghasilkan nilai yang tidak valid. Silahkan coba angka yang lebih kecil.");
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
            // Menampilkan pesan kesalahan jika kategori tidak ada
            System.out.println("Maaf! Kategori tidak ada, silahkan buat baru.");
        }
    }

    // Fungsi untuk menambahkan stok barang
    private static void penambahanStokBarang(Scanner scanner, List<List<List<String>>> gudang) {
        // Logika untuk menambahkan stok barang dalam gudang
        System.out.println("Anda memilih menu untuk menambahkan stok barang.");
        System.out.print("Silahkan inputkan kategori barang yang ingin dirubah : ");
        String kategoriBaru = scanner.nextLine();

        // Fungsinya untuk mengecek apakah kategori ada atau tidak
        boolean categoryExists = false;
        for (int indeksKategori = 0; indeksKategori < gudang.size(); indeksKategori++) {
            String namaKategori = gudang.get(indeksKategori).get(0).get(0);
            if (namaKategori.equalsIgnoreCase(kategoriBaru)) {
                categoryExists = true;
                break;
            }
        }

        // Jika kategori ada, maka akan melanjutkan program/perintah selanjutnya
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

            // Jika barang sudah ada, maka program/perintah selanjutnya akan dilanjutkan
            if (itemExists) {
                System.out.print("Silahkan inputkan jumlah stok yang ingin ditambah : ");
                String tambahStok = scanner.nextLine();
                // Input stok harus berupa angka
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