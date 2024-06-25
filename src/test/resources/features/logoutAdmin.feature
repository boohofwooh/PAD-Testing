Feature: Logout admin
  Background:
    Given user merupakan admin

  Scenario: User logout
    When user menekan tombol akun pada sidebar
    And user menekan logout
    Then user kembali ke halaman login
