# DOKUMENTASI MINI PROJECT 4

---

## SISTEM RESERVASI HOTEL

**Nama:** Aulia Aisyah Al Humaira

**NIM:** 2509116029

---

# BAB I PENDAHULUAN

## 1.1 Deskripsi Singkat

### Deskripsi Program

Sistem Tracking Reservasi Hotel merupakan program yang dirancang untuk membantu proses pengelolaan dan pemantauan data reservasi hotel secara sederhana dan terstruktur. Program ini berbasis Java dan menggunakan konsep Pemrograman Berorientasi Objek (PBO).

Program memungkinkan pengguna untuk mengelola data reservasi melalui beberapa fitur, seperti menambahkan reservasi, menampilkan data reservasi, melakukan check-in, melakukan check-out, memperbarui jumlah malam, dan menghapus reservasi.

Setiap data reservasi memiliki informasi seperti ID reservasi, nama tamu, jumlah malam, harga per malam, dan status reservasi. Status reservasi dapat berubah sesuai proses yang dilakukan, yaitu dari **Menunggu**, kemudian **Check-In**, dan setelah tamu selesai menginap menjadi **Check-Out**.

Program memiliki dua jenis reservasi, yaitu **Reservasi Standard** dan **Reservasi VIP**. Reservasi Standard memiliki kebijakan denda pembatalan sebesar 20%, sedangkan Reservasi VIP memiliki pilihan jenis penyambutan yang dapat memengaruhi biaya reservasi.

Dalam pembuatannya, program menerapkan beberapa konsep Pemrograman Berorientasi Objek (PBO), yaitu **encapsulation, inheritance, polymorphism, constructor, dan abstraction**. Program juga menggunakan pola **Model-View-Controller (MVC)** untuk memisahkan bagian data, tampilan, dan proses pengendalian program.

---

## 1.2 Tujuan

Sistem Tracking Reservasi Hotel dirancang dengan tujuan sebagai berikut:

- Membantu mengelola data reservasi hotel secara terstruktur.
- Memudahkan proses tambah, tampil, update, dan hapus data reservasi.
- Memudahkan tracking status tamu melalui proses check-in dan check-out
---

## 1.3 Alur Singkat

Alur program dimulai dengan menampilkan menu utama **Tracking Reservasi Hotel**. Pengguna dapat memilih menu sesuai dengan kebutuhan, yaitu menambah reservasi, menampilkan semua reservasi, melakukan check-in, melakukan check-out, memperbarui jumlah malam, menghapus reservasi, atau keluar dari program.

Pada saat menambahkan reservasi, pengguna terlebih dahulu memilih jenis reservasi, yaitu Standard atau VIP. Setelah itu pengguna memasukkan nama tamu dan jumlah malam.

Jika pengguna memilih reservasi Standard, sistem akan membuat data reservasi Standard dengan harga Rp300.000 per malam dan memiliki ketentuan denda pembatalan sebesar 20%.

Jika pengguna memilih reservasi VIP, pengguna dapat memilih jenis penyambutan. Jenis penyambutan tersebut terdiri dari Reguler, Executive Lounge, dan Airport Pickup + Lounge. Setiap jenis penyambutan memiliki biaya tambahan yang berbeda.

Setelah data berhasil dibuat, reservasi akan masuk ke dalam daftar reservasi dengan status **Menunggu**. Pengguna dapat melakukan check-in sehingga status berubah menjadi **Check-In**, kemudian melakukan check-out sehingga status berubah menjadi **Check-Out**.

Program akan terus berjalan sampai pengguna memilih menu **Keluar**.

---
# BAB II  STRUKTUR PACKAGE

Program menggunakan struktur package untuk memisahkan komponen berdasarkan tanggung jawabnya.

## 2.1 Package `com.mycompany.reservasihotel`

Berisi `SistemReservasiHotel.java` sebagai main class dan titik awal eksekusi program.

## 2.2 Package `config`

- `Koneksi.java`: mengatur koneksi JDBC ke MySQL.
- `TestKoneksi.java`: menguji koneksi JDBC.
- `JPAUtil.java`: menyediakan `EntityManagerFactory` untuk JPA/Hibernate.
- `TestORM.java`: menjalankan pengujian CRUD menggunakan ORM.

## 2.3 Package `controller`

Berisi `ReservasiController.java` yang memproses pilihan pengguna dan menghubungkan model, view, serta akses database.

## 2.4 Package `model`

- `Reservasi.java`: superclass yang menyimpan atribut dan perilaku umum reservasi.
- `ReservasiStandard.java`: subclass dengan aturan denda pembatalan.
- `ReservasiVIP.java`: subclass dengan layanan penyambutan dan biaya tambahan.
- `DapatDidenda.java`: interface untuk kemampuan menghitung denda pembatalan.

## 2.5 Package `model.DAO`

Berisi `ReservasiDAO.java` yang menangani operasi CRUD menggunakan JDBC dan query SQL melalui `PreparedStatement`.

## 2.6 Package `model.ORM`

Berisi `ReservasiORM.java` yang mengimplementasikan operasi CRUD melalui JPA/Hibernate dengan `EntityManager`. Method yang tersedia meliputi `tambahReservasi()`, `cariReservasiById()`, `getAllReservasi()`, `updateReservasi()`, dan `hapusReservasi()`.

## 2.7 Package `view`

Berisi `ReservasiView.java` yang menampilkan menu, daftar reservasi, status, dan pesan kepada pengguna.

### 2.8 File Konfigurasi

- `pom.xml`: mengatur dependency JDBC dan Hibernate.
- `src/main/resources/META-INF/persistence.xml`: mengatur persistence unit JPA/Hibernate.
- File SQL database, jika disertakan, digunakan untuk menyiapkan database dan tabel reservasi.
  
---

# BAB III ALUR PROGRAM

## 3.1 Menu Utama

<img width="380" height="273" alt="image" src="https://github.com/user-attachments/assets/52b89b24-3bd3-4255-b98a-5b856691de1c" />


Gambar di atas menampilkan Menu Utama dari program Sistem Tracking Reservasi Hotel. Menu ini menjadi tampilan awal yang digunakan sebagai pusat navigasi bagi pengguna dalam mengelola data reservasi hotel. Terdapat beberapa pilihan menu, yaitu Tambah Reservasi untuk menambahkan data reservasi baru, Tampilkan Reservasi untuk melihat data reservasi yang telah tersimpan, Check-In Tamu dan Check-Out Tamu untuk mengubah status reservasi, Update Jumlah Malam untuk memperbarui lama menginap, Hapus Reservasi untuk menghapus data reservasi, serta Keluar untuk mengakhiri program.

Pengguna dapat memilih menu sesuai dengan kebutuhan pengelolaan reservasi melalui pilihan nomor yang tersedia. Dengan adanya Menu Utama ini, proses pengelolaan dan tracking data reservasi hotel dapat dilakukan secara lebih terstruktur dan mudah digunakan.

---

## 3.2 Menu Tambah Reservasi

<img width="557" height="150" alt="image" src="https://github.com/user-attachments/assets/ca92f490-3e1e-423d-85d6-d846f6a43589" />

Gambar di atas menampilkan Menu Tambah Reservasi pada program Sistem Tracking Reservasi Hotel. Menu ini digunakan untuk menambahkan data reservasi baru ke dalam sistem. Pengguna dapat memilih jenis reservasi yang ingin dibuat, yaitu Reservasi Standard atau Reservasi VIP.

Reservasi Standard memiliki harga Rp300.000 per malam dengan ketentuan denda pembatalan sebesar 20%, sedangkan Reservasi VIP memiliki harga Rp750.000 per malam dengan tambahan layanan sebesar Rp100.000. Setelah memilih jenis reservasi, pengguna akan melanjutkan proses dengan memasukkan data yang diperlukan sesuai dengan jenis reservasi yang dipilih.

Dengan adanya menu ini, pengguna dapat menentukan jenis reservasi sesuai kebutuhan sebelum memasukkan data tamu dan informasi reservasi.

## 3.2.1 Tambah Reservasi Standard

<img width="541" height="173" alt="image" src="https://github.com/user-attachments/assets/d476d9e4-466f-43d1-952e-51b6368ba481" />


Gambar di atas menampilkan proses penambahan Reservasi Standard pada program Sistem Tracking Reservasi Hotel. Pengguna memilih tipe reservasi Standard, kemudian memasukkan nama tamu dan jumlah malam menginap.

Reservasi Standard memiliki atribut khusus `dendaPembatalanPersen` yang digunakan untuk menentukan persentase denda apabila reservasi dibatalkan sebelum proses check-out. Nilai denda yang digunakan dalam program adalah 20%.

Pada contoh tersebut, tamu melakukan reservasi Standard selama 2 malam. Setelah data berhasil dimasukkan, sistem memberikan ID reservasi secara otomatis.

---

## 3.2.2 Tambah Reservasi VIP

<img width="555" height="277" alt="image" src="https://github.com/user-attachments/assets/69657d4a-a4cf-42d1-af1f-79ca5f8d01f6" />

Gambar di atas menampilkan proses penambahan Reservasi VIP pada program Sistem Tracking Reservasi Hotel. Pengguna memilih tipe reservasi VIP, kemudian memasukkan nama tamu dan jumlah malam menginap. Setelah itu, pengguna memilih jenis penyambutan yang tersedia, yaitu Reguler, Executive Lounge, atau Airport Pickup + Lounge. 

Pada contoh tersebut, tamu bernama Lia melakukan reservasi selama 10 malam dan memilih jenis penyambutan Executive Lounge. Setelah data berhasil dimasukkan, sistem memberikan ID reservasi secara otomatis, yaitu ID 5.

---

## 3.3 Menu Tampilkan Semua Reservasi

<img width="887" height="307" alt="image" src="https://github.com/user-attachments/assets/82a8f258-23da-4349-97d2-4c297b17fb8a" />

Gambar di atas menampilkan Daftar Reservasi pada program Sistem Tracking Reservasi Hotel. Menu ini digunakan untuk melihat seluruh data reservasi yang telah tersimpan di dalam sistem. Informasi yang ditampilkan meliputi ID, Nama Tamu, Tipe, Malam, Status, Total Biaya, dan Detail.

Pada daftar tersebut terdapat tiga data reservasi, yaitu Karina dengan tipe Standard dan status Menunggu, Bang Chan dengan tipe VIP dan status Check-In, serta Honey bee dengan tipe Standard dan status Check-Out. Total biaya setiap reservasi ditampilkan sesuai dengan tipe dan jumlah malam. Pada reservasi VIP, bagian detail menampilkan jenis penyambutan yang digunakan beserta biaya tambahannya.

Dengan adanya menu ini, pengguna dapat melihat dan memantau data reservasi beserta status dan total biaya yang tersimpan dalam sistem.

---
## 3.4 Menu Tampilkan berdasarkan Status

<img width="903" height="297" alt="Screenshot 2026-10-03 110121" src="https://github.com/user-attachments/assets/24ede16c-cd64-4bcb-aed5-341bff06e2dc" />

Gambar di atas menampilkan menu Pilih Status pada program Sistem Tracking Reservasi Hotel. Menu ini digunakan untuk menampilkan data reservasi berdasarkan status tertentu. Pengguna dapat memilih status Menunggu, Check-In, atau Check-Out.

Pada contoh tersebut, pengguna memilih status Menunggu. Sistem kemudian menampilkan reservasi yang memiliki status Menunggu, yaitu reservasi dengan nama tamu Karina, tipe Standard, jumlah menginap 2 malam, dan total biaya Rp600.000.

Dengan adanya fitur ini, pengguna dapat melihat data reservasi berdasarkan status yang dipilih sehingga proses pemantauan reservasi menjadi lebih mudah dan teratur.

## 3.5 Menu Check-In

<img width="292" height="82" alt="Screenshot 2026-09-23 220927" src="https://github.com/user-attachments/assets/26ecf010-502e-4969-9a23-efa124020e0f" />


Gambar di atas menampilkan proses Check-In Tamu pada program Sistem Tracking Reservasi Hotel. Pengguna perlu memasukkan ID reservasi yang ingin melakukan check-in. Pada contoh tersebut, pengguna memasukkan ID reservasi 4 dan sistem menampilkan pesan bahwa check-in berhasil.

<img width="685" height="201" alt="Screenshot 2026-09-23 220945" src="https://github.com/user-attachments/assets/4091a965-78c1-4530-be4d-55a66fa5f1b5" />


Setelah proses check-in berhasil, status reservasi dengan ID 4 yang sebelumnya **Menunggu** berubah menjadi **Check-In**. Perubahan status tersebut dapat dilihat pada daftar reservasi setelah proses dilakukan.

Fitur Check-In digunakan untuk mencatat bahwa tamu telah melakukan proses masuk ke hotel sehingga status reservasi dapat diperbarui sesuai dengan kondisi tamu.

---

## 3.6 Menu Check-Out

<img width="293" height="111" alt="image" src="https://github.com/user-attachments/assets/dfe58c77-b35d-4c73-8f88-008a5452f22d" />

Gambar di atas menampilkan proses Check-Out Tamu pada program Sistem Tracking Reservasi Hotel. Pengguna perlu memasukkan ID reservasi yang ingin melakukan check-out. Pada contoh tersebut, pengguna memasukkan ID reservasi 4 dan sistem menampilkan pesan bahwa check-out berhasil.


<img width="1162" height="192" alt="image" src="https://github.com/user-attachments/assets/e5898219-7fc9-4751-9613-245a656f0b63" />

Setelah proses check-out berhasil, status reservasi dengan ID 4 yang sebelumnya **Check-In** berubah menjadi **Check-Out**. Perubahan status tersebut dapat dilihat pada daftar reservasi setelah proses dilakukan.

Fitur Check-Out digunakan untuk mencatat bahwa tamu telah selesai menginap dan keluar dari hotel. Dengan adanya fitur ini, status reservasi dapat diperbarui sesuai dengan kondisi tamu.


---

## 3.7 Menu Update

<img width="326" height="107" alt="image" src="https://github.com/user-attachments/assets/b3df9b86-ca44-4532-8489-11af040d54c1" />

Gambar di atas menampilkan proses Update Jumlah Malam pada program Sistem Tracking Reservasi Hotel. Pengguna perlu memasukkan ID reservasi yang ingin diperbarui, kemudian memasukkan jumlah malam yang baru. Pada contoh tersebut, pengguna memilih ID reservasi 5 dan mengubah jumlah malam menjadi 13 malam.

<img width="1177" height="210" alt="image" src="https://github.com/user-attachments/assets/87c2f9c8-37e4-4240-b822-36699f5babfe" />

Setelah proses update berhasil, jumlah malam pada reservasi tersebut berubah dari 10 malam menjadi 13 malam. Perubahan tersebut juga memengaruhi total biaya reservasi VIP karena total biaya dihitung berdasarkan jumlah malam dan biaya penyambutan yang dipilih.

Dengan adanya fitur ini, pengguna dapat memperbarui lama menginap pada reservasi yang masih aktif tanpa perlu membuat reservasi baru.

---

## 3.8 Menu Hapus

<img width="672" height="128" alt="image" src="https://github.com/user-attachments/assets/66c031b2-d857-4ac0-a19d-583829d04fb5" />

Gambar di atas menampilkan proses Hapus Reservasi pada program Sistem Tracking Reservasi Hotel. Pengguna perlu memasukkan ID reservasi yang ingin dihapus. Pada contoh tersebut, pengguna memilih ID reservasi 1 yang berstatus Menunggu.

<img width="1178" height="213" alt="Screenshot 2026-09-23 222535" src="https://github.com/user-attachments/assets/092e4d7b-ae5c-4c61-86e7-514e690fe45a" />


Karena reservasi Standard tersebut dibatalkan sebelum proses Check-Out, sistem menghitung denda pembatalan sebesar 20% dari total biaya reservasi. Dengan jumlah biaya Rp600.000, denda yang dikenakan adalah Rp120.000. Setelah proses selesai, data reservasi berhasil dihapus dari daftar reservasi.

Gambar berikutnya menunjukkan bahwa reservasi dengan ID 1 sudah tidak terdapat dalam daftar. Dengan adanya fitur ini, pengguna dapat menghapus data reservasi yang sudah tidak diperlukan sekaligus menerapkan aturan denda pembatalan pada Reservasi Standard.

---

## 3.9 Menu Keluar

Menu Keluar digunakan untuk menghentikan program.


---

# BAB IV PENERAPAN KONSEP PBO

## 4.1 Abstraction

<img width="470" height="77" alt="image" src="https://github.com/user-attachments/assets/2f26341c-b088-44af-8ebe-c94006285d12" />

Gambar di atas menunjukkan penerapan abstraction pada class Reservasi. Class tersebut dibuat sebagai abstract class, sehingga digunakan sebagai dasar untuk class ReservasiStandard dan ReservasiVIP, bukan untuk membuat object secara langsung.

<img width="502" height="85" alt="image" src="https://github.com/user-attachments/assets/8060c160-ea4c-48a9-946e-73482a8dea5c" />

Pada class Reservasi terdapat dua abstract method, yaitu getTipe() dan getDetailTambahan(). Kedua method tersebut hanya dideklarasikan tanpa isi dan nantinya harus diimplementasikan oleh masing-masing subclass sesuai dengan karakteristik reservasinya.

---

## 4.2 Inheritance

Inheritance digunakan untuk membuat hubungan antara superclass dan subclass.

Pada program ini:

```text
              Reservasi
                  │
          ┌───────┴────────┐
          ↓                ↓
 ReservasiStandard     ReservasiVIP
```

Class `Reservasi` menjadi superclass, sedangkan `ReservasiStandard` dan `ReservasiVIP` menjadi subclass.

### 4.2.1 Reservasi Standard

<img width="528" height="80" alt="image" src="https://github.com/user-attachments/assets/6e7fb0cb-c33c-4b4d-960c-82fbeefc1f33" />


Reservasi Standard memiliki karakteristik khusus berupa kebijakan **denda pembatalan 20%**. 

Atribut tersebut digunakan untuk menentukan jumlah denda ketika reservasi Standard dibatalkan sebelum selesai.

### 4.2.2. Reservasi VIP


<img width="460" height="120" alt="image" src="https://github.com/user-attachments/assets/a7c47971-e4ad-478a-a0ff-cbd38d48f81e" />


Class `ReservasiVIP` juga mewarisi atribut dan methoddari class `Reservasi`.

Reservasi VIP memiliki karakteristik khusus berupa **jenis penyambutan**.

Jenis penyambutan yang tersedia adalah:

- Reguler
- Executive Lounge
- Airport Pickup + Lounge

Setiap jenis penyambutan memiliki biaya tambahan yang berbeda.

---

## 4.3 Encapsulation

Encapsulation diterapkan dengan menggunakan access modifier `private` pada atribut class.

Contohnya pada class `Reservasi`:

```java
private final int idReservasi;
private final int hargaPerMalam;
private String namaTamu;
private int jumlahMalam;
private String status;
```

Atribut tersebut tidak dapat diakses secara langsung dari luar class.

Pengambilan data dilakukan melalui getter, sedangkan perubahan data dilakukan melalui setter.

<img width="375" height="76" alt="image" src="https://github.com/user-attachments/assets/520a03cc-9e35-46a2-809f-c6065454a147" />


Dengan cara tersebut, data reservasi dapat dikontrol dan divalidasi melalui method yang telah disediakan oleh class.

<img width="495" height="82" alt="image" src="https://github.com/user-attachments/assets/ee422af6-e506-4243-b608-f85ce6be24dd" />

Sedangkan untuk mengubah data digunakan setter seperti gambar di atas

---

## 4.4 Constructor

Constructor digunakan untuk memberikan nilai awal ketika object dibuat.

Contohnya pada class Reservasi:

``` public Reservasi(int idReservasi, String namaTamu,
        int jumlahMalam, int hargaPerMalam) {

    this.idReservasi = idReservasi;
    this.hargaPerMalam = hargaPerMalam;
    this.status = MENUNGGU;

    setNamaTamu(namaTamu);
    setJumlahMalam(jumlahMalam);
}
```

Constructor digunakan untuk menginisialisasi ID reservasi, nama tamu, jumlah malam, harga per malam, dan status awal reservasi.

Pada subclass digunakan super() untuk memanggil constructor superclass.

Contohnya:


    public ReservasiStandard(int idReservasi, String namaTamu,
        int jumlahMalam) {
      super(idReservasi, namaTamu, jumlahMalam, 300000);
    setDendaPersen(20)
}


---

## 4.5 Polymorphism

### 4.5.1 Method Overloading

<img width="892" height="412" alt="image" src="https://github.com/user-attachments/assets/c6ce62d0-d29f-479b-aa4b-d2490a506ea3" />

Gambar di atas menunjukkan penerapan method overloading pada class ReservasiView. Method tampilkanDaftar() dibuat sebanyak dua kali dengan nama yang sama, tetapi memiliki parameter yang berbeda.

Method pertama menerima parameter ArrayList<Reservasi> dan digunakan untuk menampilkan seluruh data reservasi. Sedangkan method kedua menerima parameter ArrayList<Reservasi> dan String status yang digunakan untuk menampilkan data reservasi berdasarkan status tertentu.

Dengan adanya method overloading, satu nama method dapat digunakan untuk beberapa kebutuhan yang berbeda berdasarkan parameter yang diberikan.

### 4.5.2 Method Overriding
Saya menerapkan method overriding pada class ReservasiStandard dan ReservasiVIP. Method overriding terjadi ketika subclass membuat kembali method yang sudah didefinisikan pada superclass Reservasi dengan implementasi yang disesuaikan dengan jenis reservasinya.

<img width="606" height="356" alt="image" src="https://github.com/user-attachments/assets/dd7c4dd4-08ee-4340-ab3a-ac1071d632af" />

Pada ReservasiStandard, terdapat overriding pada method getTipe() dan getDetailTambahan(). Method getTipe() mengembalikan nilai "Standard", sedangkan getDetailTambahan() menampilkan informasi mengenai denda pembatalan.

<img width="725" height="397" alt="image" src="https://github.com/user-attachments/assets/230ff704-8ecb-4048-90a5-e1f8945d0a9e" />

Pada ReservasiVIP, method getTipe() mengembalikan nilai **"VIP", sedangkan getDetailTambahan()menampilkan jenis penyambutan dan biaya tambahannya. Selain itu,hitungTotalBiaya()` juga dioverride untuk menambahkan biaya penyambutan ke dalam total biaya reservasi VIP.

Dengan adanya method overriding, method yang sama dapat memiliki perilaku yang berbeda sesuai dengan subclass yang digunakan.

## 4.6 Interface 

<img width="572" height="222" alt="image" src="https://github.com/user-attachments/assets/264a90f7-320a-4e91-a0d9-afc8c68b4b0d" />

Gambar di atas menunjukkan penerapan interface pada program Sistem Tracking Reservasi Hotel. Program menggunakan interface DapatDidenda untuk menentukan method yang digunakan dalam menghitung denda pembatalan.

<img width="826" height="61" alt="Screenshot 2026-10-03 112314" src="https://github.com/user-attachments/assets/46b34826-c328-4708-8539-87fa047364ed" />

Dengan implements DapatDidenda, class ReservasiStandard harus menyediakan implementasi dari method yang terdapat pada interface tersebut, yaitu hitungDenda().

<img width="668" height="112" alt="Screenshot 2026-10-03 112325" src="https://github.com/user-attachments/assets/eaeb2a96-85af-4681-b8a1-ed2cb6343890" />

Method tersebut digunakan untuk menghitung denda pembatalan berdasarkan total biaya reservasi dan persentase denda yang dimiliki oleh Reservasi Standard.
Dengan demikian, interface DapatDidenda memberikan aturan bahwa class yang mengimplementasikannya harus memiliki kemampuan untuk menghitung denda.


---

# BAB V INTEGRASI DATABASE MENGGUNAKAN JDBC

## 5.1 Koneksi Database

<img width="821" height="313" alt="image" src="https://github.com/user-attachments/assets/78c74ac6-7666-441d-b5fd-203e9142908e" />

## 5.2 Implementasi PreparedStatement dan CRUD

<img width="1060" height="622" alt="image" src="https://github.com/user-attachments/assets/229273d8-24f7-40e3-8ba0-028a89145e14" />

ReservasiDAO.java menerapkan pola Data Access Object (DAO) untuk memisahkan akses database dari controller. Operasi CRUD dilakukan menggunakan query SQL dan PreparedStatement, sehingga input pengguna diberikan melalui parameter yang sesuai. Pada gambar di atas digunakan untuk menambah data.

<img width="711" height="138" alt="image" src="https://github.com/user-attachments/assets/8884cd02-f584-42a7-9872-840a6f1bf320" />

Gambar di atas berfungsi untuk menampilkan seluruh data yang sebelumnya sudah di tambahkan di dalam database.

<img width="968" height="132" alt="image" src="https://github.com/user-attachments/assets/ac8ee410-0cf8-4bfd-9047-c761b0bd7b86" />


Gambar di atas berfungsi untuk mengubah data yang sebelumnya sudah di tambahkan di dalam database.

<img width="651" height="96" alt="image" src="https://github.com/user-attachments/assets/12b8d9d9-dfb4-45c6-9269-2b50603378ce" />

Gambar di atas berfungsi untuk menghapus data yang sebelumnya sudah di tambahkan di dalam database.

## 5.3 Pengujian Koneksi

<img width="693" height="245" alt="image" src="https://github.com/user-attachments/assets/83e3b0fc-21d5-4241-9c2d-06776858997a" />

Pengujian dilakukan menggunakan TestKoneksi.java untuk memastikan koneksi antara Java dan MySQL berhasil. Keberhasilan koneksi ditunjukkan melalui pesan pada console bahwa database siap digunakan.

<img width="713" height="200" alt="image" src="https://github.com/user-attachments/assets/773063c9-84c4-45db-b56a-2f83156b2002" />


# BAB VI Penerapan Nilai Tambah ORM

## 6.1 Konfigurasi ORM

<img width="777" height="288" alt="image" src="https://github.com/user-attachments/assets/e9762855-859e-439c-aa06-7b1384f470b6" />

JPAUtil.java digunakan untuk menyediakan EntityManager melalui konfigurasi JPA/Hibernate. EntityManager menjadi sarana untuk mengelola objek reservasi yang dipetakan sebagai entity dan menjalankan operasi database menggunakan ORM.

## 6.2 Implementasi CRUD ORM

### 6.2.1 Create 

<img width="345" height="92" alt="image" src="https://github.com/user-attachments/assets/3ba88fbd-45c8-4e82-80e2-6ad09ced88b4" />

Gambar di atas berfungsi untuk menambahkan  data yang sebelumnya sudah di tambahkan di dalam database.

### 6.2.2 Read
<img width="895" height="187" alt="image" src="https://github.com/user-attachments/assets/3284145c-0524-4b16-ba1b-9acca0a3db33" />

Gambar di atas berfungsi untuk menampilkan seluruh data yang sebelumnya sudah di tambahkan di dalam database.


### 6.2.3 Update

<img width="557" height="161" alt="image" src="https://github.com/user-attachments/assets/b831f0a8-0b57-4591-897a-27071fca9eac" />

Gambar di atas berfungsi untuk mengubah data yang sebelumnya sudah di tambahkan di dalam database.

### 6.2.4 Delete
<img width="370" height="91" alt="image" src="https://github.com/user-attachments/assets/4978df27-19bd-4429-ba50-5ac8a2f41a13" />

Gambar di atas berfungsi untuk menghapus data yang sebelumnya sudah di tambahkan di dalam database.


## 6.3 Pengujian ORM

<img width="672" height="422" alt="image" src="https://github.com/user-attachments/assets/f1d8de9e-93d2-4105-95ed-65de0fda2d17" />

<img width="1776" height="272" alt="image" src="https://github.com/user-attachments/assets/bc8e5750-9bee-49f9-9058-7704864cf98b" />

gambar-gambar di atas menunjukkan pengujian ORM, pengguna tidak perlu input-input karena sudah terotomisasi.

# BAB VII KESIMPULAN

Sistem Reservasi Hotel merupakan program Java yang digunakan untuk mengelola dan memantau data reservasi hotel secara sederhana.

Program menyediakan fitur tambah reservasi, tampilkan reservasi, check-in, check-out, update jumlah malam, dan hapus reservasi. Program juga membedakan reservasi menjadi Standard dan VIP dengan karakteristik dan aturan biaya yang berbeda.

Dalam pembuatannya, program menerapkan konsep Pemrograman Berorientasi Objek seperti **encapsulation, inheritance, polymorphism, constructor, getter dan setter, interface, dan abstraction, JDBC dan ORM**. Selain itu, program menggunakan pola **Model-View-Controller (MVC)** untuk memisahkan bagian model, tampilan, dan proses pengendalian.

Dengan penerapan konsep tersebut, program menjadi lebih terstruktur dan setiap class memiliki fungsi yang lebih jelas dalam pengelolaan sistem reservasi hotel.
