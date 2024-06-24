Feature: Manajemen farmer
  Background:
    Given User login sebagai admin

  Scenario: User dapat melihat daftar farmer
    Given user berada di halaman dashboard
    When user menekan daftar farmer pada sidebar
    Then tabel daftar farmer ditampilkan di halaman daftar farmer

  Scenario: User dapat mencari farmer
    Given user berada di halaman daftar farmer
    When user memasukkan nama "furina" pada searchbar
    Then data farmer "furina" ditampilkan pada halaman

  Scenario: User dapat menambahkan farmer
    Given user berada di halaman daftar farmer
    When user menekan tombol add
    And user memasukkan nama "salma", email "salman@gmail.com", password "12345678", dan alamat "semarang"
    Then user "salma" harus diarahkan ke halaman daftar farmer

  Scenario: User melihat detail farmer
    Given user berada di halaman daftar farmer
    When user menekan nama farmerjhghhgg
    Then user masuk ke halaman detail farmer

  Scenario: User dapat menghapus farmer
    Given user berada di halaman detail farmer
    When user menekan tombol delete
    Then muncul pesan pada halaman daftar farmer

