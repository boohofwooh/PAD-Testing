package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
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
import page.*;

import java.time.Duration;
import java.util.List;

public class ManagementFarmerSteps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardAdminPage dashboardAdminPage;
    DaftarFarmerPage daftarFarmerPage;
    DetailFarmerPage detailFarmerPage;
    DaftarSensorPage daftarSensorPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8000/login"); // Replace with your website URL
        loginPage = new LoginPage(driver);
        dashboardAdminPage = new DashboardAdminPage(driver);
        daftarFarmerPage = new DaftarFarmerPage(driver);
        detailFarmerPage = new DetailFarmerPage(driver);
        daftarSensorPage = new DaftarSensorPage(driver);
        test = extent.createTest("Management Farmer Test");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    @Given("User login sebagai admin")
    public void user_login_sebagai_admin() {
        try {
            test.info("User attempting to log in as admin");
            loginPage.fillEmail("AdminSmf@gmail.com");
            loginPage.fillPassword("12345678");
            loginPage.clickLogin();
            test.pass("User logged in as admin");
        } catch (Exception e) {
            test.fail("Failed to log in as admin: " + e.getMessage());
            throw e;
        }
    }

    @Given("user berada di halaman dashboard")
    public void user_berada_di_halaman_dashboard() {
        try {
            test.info("User verifying they are on the dashboard page");
            Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/dashboard/admin-dashboard"));
            test.pass("User is on the dashboard page");
        } catch (AssertionError e) {
            test.fail("User is not on the dashboard page: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan daftar farmer pada sidebar")
    public void user_menekan_daftar_farmer_pada_sidebar() {
        try {
            test.info("User clicking on daftar farmer in sidebar");
            dashboardAdminPage.clickSidebarDaftarFarmer();
            test.pass("User clicked on daftar farmer in sidebar");
        } catch (Exception e) {
            test.fail("Failed to click on daftar farmer in sidebar: " + e.getMessage());
            throw e;
        }
    }

    @Then("tabel daftar farmer ditampilkan di halaman daftar farmer")
    public void tabel_daftar_farmer_ditampilkan_di_halaman_daftar_farmer() {
        try {
            test.info("Verifying that the daftar farmer table is displayed");
            boolean isDisplayed = daftarFarmerPage.isDaftarFarmerDisplayed();
            Assertions.assertTrue(isDisplayed);
            Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
            test.pass("Daftar farmer table is displayed on the page");
        } catch (AssertionError e) {
            test.fail("Daftar farmer table is not displayed: " + e.getMessage());
            throw e;
        }
    }

    @Given("user berada di halaman daftar farmer")
    public void user_berada_di_halaman_daftar_farmer() {
        try {
            test.info("Navigating to halaman daftar farmer");
            dashboardAdminPage.clickSidebarDaftarFarmer();
            Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
            test.pass("User is on halaman daftar farmer");
        } catch (Exception e) {
            test.fail("Failed to navigate to halaman daftar farmer: " + e.getMessage());
            throw e;
        }
    }

    @When("user memasukkan nama {string} pada searchbar")
    public void user_memasukkan_nama_pada_searchbar(String name) {
        try {
            test.info("Entering name in searchbar: " + name);
            daftarFarmerPage.searchFarmerByName(name);
            test.pass("Entered name in searchbar: " + name);
        } catch (Exception e) {
            test.fail("Failed to enter name in searchbar: " + e.getMessage());
            throw e;
        }
    }

    @Then("data farmer {string} ditampilkan pada halaman")
    public void data_farmer_ditampilkan_pada_halaman(String name) {
        try {
            test.info("Verifying that farmer data is displayed for: " + name);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table-farmer")));

            List<WebElement> farmerNames = driver.findElements(By.xpath("//*[@id='table-farmer']/tbody/tr/td[2]/form/button/div/p"));
            boolean isFarmerDisplayed = farmerNames.stream()
                    .anyMatch(element -> element.getText().equalsIgnoreCase(name));

            Assertions.assertTrue(isFarmerDisplayed);
            test.pass("Farmer data displayed for: " + name);
        } catch (Exception e) {
            test.fail("Failed to display farmer data for: " + name + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan tombol add")
    public void user_menekan_tombol_add(){
        try {
            test.info("Clicking add button");
            daftarFarmerPage.addButtonClick();
            test.pass("Add button clicked");
        } catch (Exception e) {
            test.fail("Failed to click add button: " + e.getMessage());
            throw e;
        }
    }

    @And("user memasukkan nama {string}, email {string}, password {string}, dan alamat {string}")
    public void user_memasukkan_nama_email_password_dan_alamat(String name, String email, String password, String address){
        try {
            test.info("Entering farmer details: name=" + name + ", email=" + email + ", password=" + password + ", address=" + address);
            daftarFarmerPage.modalFill(name, email, password, address);
            test.pass("Entered farmer details");
        } catch (Exception e) {
            test.fail("Failed to enter farmer details: " + e.getMessage());
            throw e;
        }
    }

    @Then("user {string} harus diarahkan ke halaman daftar farmer")
    public void user_harus_diarahkan_ke_halaman_daftar_farmer(String name){
        try {
            test.info("Verifying that user is redirected to halaman daftar farmer");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table-farmer")));

            List<WebElement> farmerNames = driver.findElements(By.xpath("//*[@id='table-farmer']/tbody/tr/td[2]/form/button/div/p"));
            boolean isFarmerDisplayed = farmerNames.stream()
                    .anyMatch(element -> element.getText().equalsIgnoreCase(name));

            Assertions.assertTrue(isFarmerDisplayed);
            Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
            test.pass("User redirected to halaman daftar farmer and farmer displayed: " + name);
        } catch (Exception e) {
            test.fail("Failed to redirect to halaman daftar farmer or display farmer: " + name + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan nama farmer")
    public void user_menekan_nama_farmer(){
        try {
            test.info("Clicking on farmer name");
            daftarFarmerPage.farmerDetail();
            test.pass("Farmer name clicked");
        } catch (Exception e) {
            test.fail("Failed to click on farmer name: " + e.getMessage());
            throw e;
        }
    }

    @Then("user masuk ke halaman detail farmer")
    public void user_masuk_ke_halaman_detail_farmer(){
        try {
            test.info("Verifying that user is on halaman detail farmer");
            Assertions.assertTrue(driver.getCurrentUrl().contains("/read-farmer"));
            test.pass("User is on halaman detail farmer");
        } catch (AssertionError e) {
            test.fail("User is not on halaman detail farmer: " + e.getMessage());
            throw e;
        }
    }

    @Given("user berada di halaman detail farmer")
    public void user_berada_di_halaman_detail_farmer(){
        try {
            test.info("Navigating to halaman detail farmer");
            user_berada_di_halaman_daftar_farmer();
            user_menekan_nama_farmer();
            Assertions.assertTrue(driver.getCurrentUrl().contains("/read-farmer"));
            test.pass("User is on halaman detail farmer");
        } catch (Exception e) {
            test.fail("Failed to navigate to halaman detail farmer: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan tombol delete")
    public void user_menekan_tombol_delete(){
        try {
            test.info("Clicking delete button");
            detailFarmerPage.deleteClick();
            test.pass("Delete button clicked");
        } catch (Exception e) {
            test.fail("Failed to click delete button: " + e.getMessage());
            throw e;
        }
    }

    @Then("muncul pesan pada halaman daftar farmer")
    public void muncul_pesan_pada_halaman_daftar_farmer(){
        try {
            test.info("Verifying that message is displayed on halaman daftar farmer");
            boolean isDisplayed = detailFarmerPage.isMessageDisplayed();
            Assertions.assertTrue(isDisplayed);
            Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-farmer"));
            test.pass("Message displayed on halaman daftar farmer");
        } catch (AssertionError e) {
            test.fail("Message is not displayed on halaman daftar farmer: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan tombol edit")
    public void user_menekan_tombol_edit(){
        try {
            test.info("Clicking edit button");
            detailFarmerPage.editClicked();
            test.pass("Edit button clicked");
        } catch (Exception e) {
            test.fail("Failed to click edit button: " + e.getMessage());
            throw e;
        }
    }

    @And("user mengubah data farmer")
    public void user_mengubah_data_farmer() {
        try {
            test.info("User modifying farmer data");
            detailFarmerPage.editUser("momon", "momos@gmail.com", "12345678", "bandungan");
            test.pass("User data entered for modification");

            detailFarmerPage.saveBtnClicked();
            test.pass("Save button clicked");

        } catch (Exception e) {
            test.fail("Failed to modify farmer data: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan tombol akun pada sidebar")
    public void user_menekan_tombol_akun_pada_sidebar(){
        test.info("User clicks on account button in sidebar");
        dashboardAdminPage.accountBtnClick();
        test.pass("Account button clicked");
    }

    @And("user menekan logout")
    public void user_menekan_logout(){
        test.info("User clicks on logout button");
        dashboardAdminPage.logoutClick();
        test.pass("Logout button clicked");
    }

    @Then("user kembali ke halaman login")
    public void user_kembali_ke_halaman_login(){
        test.info("Verifying user is redirected to login page");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
        test.pass("User is redirected to login page");
    }



}
