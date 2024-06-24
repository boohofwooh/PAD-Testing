package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.DaftarFarmerPage;
import page.DashboardAdminPage;
import page.DetailFarmerPage;
import page.LoginPage;

import java.time.Duration;
import java.util.List;

public class ManagementFarmerSteps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardAdminPage dashboardAdminPage;
    DaftarFarmerPage daftarFarmerPage;
    DetailFarmerPage detailFarmerPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8000/login"); // Replace with your website URL
        loginPage = new LoginPage(driver);
        dashboardAdminPage = new DashboardAdminPage(driver);
        daftarFarmerPage = new DaftarFarmerPage(driver);
        detailFarmerPage = new DetailFarmerPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User login sebagai admin")
    public void user_login_sebagai_admin() {
        loginPage.fillEmail("AdminSmf@gmail.com");
        loginPage.fillPassword("12345678");
        loginPage.clickLogin();
    }

    @Given("user berada di halaman dashboard")
    public void user_berada_di_halaman_dashboard() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/dashboard/admin-dashboard"));
    }

    @When("user menekan daftar farmer pada sidebar")
    public void user_menekan_daftar_farmer_pada_sidebar() {
        dashboardAdminPage.clickSidebarDaftarFarmer();
    }

    @Then("tabel daftar farmer ditampilkan di halaman daftar farmer")
    public void tabel_daftar_farmer_ditampilkan_di_halaman_daftar_farmer() {
        boolean isDisplayed = daftarFarmerPage.isDaftarFarmerDisplayed();
        Assertions.assertTrue(isDisplayed);
        Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
    }

    @Given("user berada di halaman daftar farmer")
    public void user_berada_di_halaman_daftar_farmer() {
        dashboardAdminPage.clickSidebarDaftarFarmer();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
    }

    @When("user memasukkan nama {string} pada searchbar")
    public void user_memasukkan_nama_pada_searchbar(String name) {
        daftarFarmerPage.searchFarmerByName(name);
    }

    @Then("data farmer {string} ditampilkan pada halaman")
    public void data_farmer_ditampilkan_pada_halaman(String name) {
        // Tunggu hingga elemen yang mengandung data petani muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table-farmer")));

        // Verifikasi apakah nama petani ada dalam tabel
        List<WebElement> farmerNames = driver.findElements(By.xpath("//*[@id='table-farmer']/tbody/tr/td[2]/form/button/div/p"));
        boolean isFarmerDisplayed = farmerNames.stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase(name));

        Assertions.assertTrue(isFarmerDisplayed);
    }

    @When("user menekan tombol add")
    public void user_menekan_tombol_add(){
        daftarFarmerPage.addButtonClick();
    }

    @And("user memasukkan nama {string}, email {string}, password {string}, dan alamat {string}")
    public void user_memasukkan_nama_email_password_dan_alamat(String name, String email, String password, String address){
        daftarFarmerPage.modalFill(name, email, password, address);
    }

    @Then("user {string} harus diarahkan ke halaman daftar farmer")
    public void user_harus_diarahkan_ke_halaman_daftar_farmer(String name){
        // Tunggu hingga elemen yang mengandung data petani muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table-farmer")));

        // Verifikasi apakah nama petani ada dalam tabel
        List<WebElement> farmerNames = driver.findElements(By.xpath("//*[@id='table-farmer']/tbody/tr/td[2]/form/button/div/p"));
        boolean isFarmerDisplayed = farmerNames.stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase(name));

        Assertions.assertTrue(isFarmerDisplayed);
        Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
    }

    @When("user menekan nama farmer")
    public void user_menekan_nama_farmer(){
        daftarFarmerPage.farmerDetail();
    }

    @Then("user masuk ke halaman detail farmer")
    public void user_masuk_ke_halaman_detail_farmer(){
        Assertions.assertTrue(driver.getCurrentUrl().contains("/read-farmer"));
    }

    @Given("user berada di halaman detail farmer")
    public void user_berada_di_halaman_detail_farmer(){
        user_berada_di_halaman_daftar_farmer();
        user_menekan_nama_farmer();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/read-farmer"));
    }

    @When("user menekan tombol delete")
    public void user_menekan_tombol_delete(){
        detailFarmerPage.deleteClick();
    }

    @Then("muncul pesan pada halaman daftar farmer")
    public void muncul_pesan_pada_halaman_daftar_farmer(){
        boolean isDisplayed = detailFarmerPage.isMessageDisplayed();
        Assertions.assertTrue(isDisplayed);
        Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
    }

}
