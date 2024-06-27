package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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
import page.*;

import java.time.Duration;
import java.util.List;

public class ManagementLahanSteps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardAdminPage dashboardAdminPage;
    DaftarLahanPage daftarLahanPage;
    DetailLahanPage detailLahanPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8000/login"); // Replace with your website URL
        loginPage = new LoginPage(driver);
        dashboardAdminPage = new DashboardAdminPage(driver);
        daftarLahanPage = new DaftarLahanPage(driver);
        daftarLahanPage = new DaftarLahanPage(driver);
        detailLahanPage = new DetailLahanPage(driver);
        test = extent.createTest("Management Lahan Test");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    @Given("user adalah admin")
    public void user_adalah_admin(){
        try {
            loginPage.fillEmail("AdminSmf@gmail.com");
            loginPage.fillPassword("12345678");
            loginPage.clickLogin();
            test.pass("User logged in as admin");
        } catch (Exception e) {
            test.fail("User failed to log in as admin: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan daftar lahan pada sidebar")
    public void user_menekan_daftar_lahan_pada_sidebar(){
        try {
            test.info("User clicking on daftar sensor in sidebar");
            dashboardAdminPage.clickSidebarDaftarLahan();
            test.pass("User clicked on daftar sensor in sidebar");
        } catch (Exception e) {
            test.fail("Failed to click on daftar sensor in sidebar: " + e.getMessage());
            throw e;
        }
    }

    @Then("tabel daftar lahan ditampilkan di halaman daftar lahan")
    public void tabel_daftar_lahan_ditampilkan_di_halaman_daftar_farmer(){
        try {
            test.info("Verifying that the daftar sensor table is displayed");
            boolean isDisplayed = daftarLahanPage.isDaftarLahanDisplayed();
            Assertions.assertTrue(isDisplayed);
            Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-lahan"));
            test.pass("Daftar sensor table is displayed on the page");
        } catch (AssertionError e) {
            test.fail("Daftar sensor table is not displayed: " + e.getMessage());
            throw e;
        }
    }

    @Given("user berada di halaman daftar lahan")
    public void user_berada_di_halaman_daftar_lahan(){
        dashboardAdminPage.clickSidebarDaftarLahan();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-lahan"));
    }

    @When("user menekan tombol add lahan")
    public void user_menekan_tombol_add_lahan(){
        daftarLahanPage.addLahanBtnClick();
    }

    @Then("modal tambah lahan ditampilkan")
    public void modal_tambah_lahan_ditampilkan(){
        boolean isModalDisplayed = driver.findElement(By.xpath("//*[contains(text(), 'Tambah Lahan')]")).isDisplayed();
        Assertions.assertTrue(isModalDisplayed, "The modal should be displayed");
    }

    @When("user memasukkan id lahan {string} pada searchbar")
    public void user_memasukkan_id_lahan_pada_searchbar(String id){
        daftarLahanPage.searchLahan(id);
    }

    @Then("data id lahan {string} ditampilkan")
    public void data_id_lahan_ditampilkan(String id){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table")));

        List<WebElement> rows = driver.findElements(By.xpath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table/tbody"));
        boolean isSensorDisplayed = rows.stream()
                .anyMatch(row -> row.getText().contains(id));

        Assertions.assertTrue(isSensorDisplayed, "Lahan ID " + id + " should be displayed");
    }

    @When("user menekan id lahan")
    public void user_menekan_id_lahan(){
        daftarLahanPage.lahanDetail();
    }

    @Then("user masuk ke halaman detail lahan")
    public void user_masuk_ke_halaman_detail_lahan(){
        Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/edit-delete/read-lahan/"));
    }

    @Given("user berada di halaman detail lahan")
    public void user_berada_di_halaman_detail_sensor(){
        user_berada_di_halaman_daftar_lahan();
        user_menekan_id_lahan();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/read-lahan"));
    }

    @When("user menekan tombol delete lahan")
    public void user_menekan_tombol_delete_lahan(){
        detailLahanPage.btnDeleteClick();
    }

    @Then("muncul pesan pada halaman daftar lahan")
    public void muncul_pesan_pada_halaman_daftar_lahan(){
        detailLahanPage.isMessageDisplayed();
    }
}
