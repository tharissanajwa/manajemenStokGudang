import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManajemenGudang {
    public static ArrayList<String> tambahBarang (String kategori, String namaBarang, String stok) {
        ArrayList<String> barang = new ArrayList<>();
        barang.add(kategori);
        barang.add(namaBarang);
        barang.add(stok);
        return barang;
    }

    public static void main() {

        List<List<List<String>>> gudang = new ArrayList<>();

        // Membuat Data Sample
        gudang.add(new ArrayList<>());
        gudang.get(0).add(tambahBarang("Pakaian", "Baju Tidur", "15"));
        gudang.get(0).add(tambahBarang("Pakaian", "Jaket Palka", "25"));

        gudang.add(new ArrayList<>());
        gudang.get(1).add(tambahBarang("Makanan", "Nasi Merah", "20"));
        gudang.get(1).add(tambahBarang("Makanan", "Donat Aceh", "50"));

        // Welcome Output
        System.out.println("Selamat Datang di Manajemen Gudang Tharissa!");

        // Menyimpan Variabel Data
        boolean loopingMenu = true;
        Scanner scanner = new Scanner(System.in);
        while (loopingMenu) {
            tampilkanMenu();
            int pilihanUser = getPilihanUser(scanner);

            switch (pilihanUser) {
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
                case 0:
                    System.out.println("Anda memilih untuk keluar dari program.");
                    System.out.println("Terimakasih atas waktunya.");
                    System.out.println("=====================================================================================");
                    loopingMenu = false;
                    break;
                case -1:
                    System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                    scanner.nextLine();
                    break;
            }
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
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

    private static int getPilihanUser(Scanner scanner) {
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

    private static void tambahBarangBaru(Scanner scanner, List<List<List<String>>> gudang) {
        System.out.println("Anda memilih menu untuk menambahkan barang baru.");
        System.out.print("Silahkan inputkan kategori barang yang diinginkan : ");
        String kategoriBaru = scanner.nextLine();

        // cek kategori ada atau tidak
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

        if (!categoryExists) {
            gudang.add(new ArrayList<>());
            categoryIndex = gudang.size() - 1;
        }

        System.out.print("Silahkan inputkan nama barang yang diinginkan : ");
        String tambahNamaBarang = scanner.nextLine();
        System.out.print("Silahkan inputkan stok barang yang diinginkan : ");
        String addStokBarang = scanner.nextLine();
        if (addStokBarang.matches("^[0-9]+$")) {
            int tambahStokBarang = Integer.parseInt(addStokBarang);
            if (tambahStokBarang > 0) {
                // cek bila barang sudah ada
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

    private static void penguranganStokBarang(Scanner scanner, List<List<List<String>>> gudang) {
        System.out.println("Anda memilih menu untuk mengurangi stok barang.");
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

            // cek bila barang sudah ada
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

    private static void penambahanStokBarang(Scanner scanner, List<List<List<String>>> gudang) {
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

            // cek bila barang sudah ada
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

    private static void lihatStokBarang(List<List<List<String>>> gudang) {
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