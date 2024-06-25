Feature: Manajemen Lahan
  Background:
    Given user adalah admin

  Scenario: User dapat berpindah halaman ke halaman daftar lahan
    When user menekan daftar lahan pada sidebar
    Then tabel daftar lahan ditampilkan di halaman daftar lahan

  Scenario: User dapat menambahkan lahan
    Given user berada di halaman daftar lahan
    When user menekan tombol add lahan
    Then modal tambah lahan ditampilkan

  Scenario: User dapat mencari lahan
    Given user berada di halaman daftar lahan
    When user memasukkan id lahan "L006" pada searchbar
    Then data id lahan "L006" ditampilkan

  Scenario: User dapat melihat detail lahan
    Given user berada di halaman daftar lahan
    When user menekan id lahan
    Then user masuk ke halaman detail lahan
#
  Scenario: User dapat menghapus lahan
    Given user berada di halaman detail lahan
    When user menekan tombol delete lahan
    Then muncul pesan pada halaman daftar lahan




