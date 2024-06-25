Feature: Manajemen Sensor
  Background:
    Given User login sebagai admin

  Scenario: User dapat berpindah halaman ke halaman daftar sensor
    Given user berada di halaman dashboard
    When user menekan daftar sensor pada sidebar
    Then tabel daftar sensor ditampilkan di halaman daftar sensor

