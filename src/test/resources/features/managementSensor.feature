Feature: Manajemen Sensor
  Background:
    Given user login sebagai admin

  Scenario: User dapat berpindah halaman ke halaman daftar sensor
    When user menekan daftar sensor pada sidebar
    Then tabel daftar sensor ditampilkan di halaman daftar sensor

  Scenario: User dapat menambahkan sensor
    Given user berada di halaman daftar sensor
    When user menekan tombol add sensor
    Then modal tambah sensor ditampilkan

  Scenario: User dapat mencari sensor
    Given user berada di halaman daftar sensor
    When user memasukkan id sensor "S009" pada searchbar
    Then data id sensor "S009" ditampilkan

  Scenario: User dapat melihat detail sensor
    Given user berada di halaman daftar sensor
    When user menekan id sensor
    Then user masuk ke halaman detail sensor

  Scenario: User dapat menghapus sensor
    Given user berada di halaman detail sensor
    When user menekan tombol delete sensor
    Then muncul pesan pada halaman daftar sensor




