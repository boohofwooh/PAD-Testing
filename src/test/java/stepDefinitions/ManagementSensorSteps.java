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

public class ManagementSensorSteps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardAdminPage dashboardAdminPage;
    DaftarSensorPage daftarSensorPage;
    DetailSensorPage detailSensorPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8000/login"); // Replace with your website URL
        loginPage = new LoginPage(driver);
        dashboardAdminPage = new DashboardAdminPage(driver);
        daftarSensorPage = new DaftarSensorPage(driver);
        detailSensorPage = new DetailSensorPage(driver);
        test = extent.createTest("Management Sensor Test");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    @Given("user login sebagai admin")
    public void user_login_sebagai_admin() {
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

    @When("user menekan daftar sensor pada sidebar")
    public void user_menekan_daftar_sensor_pada_sidebar() {
        try {
            test.info("User clicking on daftar sensor in sidebar");
            dashboardAdminPage.clickSidebarDaftarSensor();
            test.pass("User clicked on daftar sensor in sidebar");
        } catch (Exception e) {
            test.fail("Failed to click on daftar sensor in sidebar: " + e.getMessage());
            throw e;
        }
    }

    @Then("tabel daftar sensor ditampilkan di halaman daftar sensor")
    public void tabel_daftar_sensor_ditampilkan_di_halaman_daftar_sensor() {
        try {
            test.info("Verifying that the daftar sensor table is displayed");
            boolean isDisplayed = daftarSensorPage.isDaftarSensorDisplayed();
            Assertions.assertTrue(isDisplayed);
            Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-sensor"));
            test.pass("Daftar sensor table is displayed on the page");
        } catch (AssertionError e) {
            test.fail("Daftar sensor table is not displayed: " + e.getMessage());
            throw e;
        }
    }

    @Given("user berada di halaman daftar sensor")
    public void user_berada_di_halaman_daftar_sensor() {
        try {
            dashboardAdminPage.clickSidebarDaftarSensor();
            Assertions.assertTrue(driver.getCurrentUrl().contains("/add/daftar-sensor"));
            test.pass("User is on the daftar sensor page");
        } catch (AssertionError e) {
            test.fail("User is not on the daftar sensor page: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan tombol add sensor")
    public void user_menekan_tombol_add_sensor() {
        try {
            daftarSensorPage.addSensorClicked();
            test.pass("User clicked the add sensor button");
        } catch (Exception e) {
            test.fail("Failed to click the add sensor button: " + e.getMessage());
            throw e;
        }
    }

    @Then("modal tambah sensor ditampilkan")
    public void modal__tambah_sensor_ditampilkan() {
        try {
            boolean isModalDisplayed = driver.findElement(By.xpath("//*[contains(text(), 'Tambah Sensor')]")).isDisplayed();
            Assertions.assertTrue(isModalDisplayed, "The modal should be displayed");
            test.pass("The add sensor modal is displayed");
        } catch (AssertionError e) {
            test.fail("The add sensor modal is not displayed: " + e.getMessage());
            throw e;
        }
    }

    @When("user memasukkan id sensor {string} pada searchbar")
    public void user_memasukkan_id_sensor_pada_searchbar(String id) {
        try {
            daftarSensorPage.searchSensor(id);
            test.pass("User entered sensor ID " + id + " in the search bar");
        } catch (Exception e) {
            test.fail("Failed to enter sensor ID in the search bar: " + e.getMessage());
            throw e;
        }
    }

    @Then("data id sensor {string} ditampilkan")
    public void data_id_sensor_ditampilkan(String id) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table")));

            List<WebElement> rows = driver.findElements(By.xpath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table/tbody/tr"));
            boolean isSensorDisplayed = rows.stream()
                    .anyMatch(row -> row.getText().contains(id));

            Assertions.assertTrue(isSensorDisplayed, "Sensor ID " + id + " should be displayed");
            test.pass("Sensor ID " + id + " is displayed");
        } catch (AssertionError e) {
            test.fail("Sensor ID " + id + " is not displayed: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan id sensor")
    public void user_menekan_id_sensor() {
        try {
            daftarSensorPage.sensorDetail();
            test.pass("User clicked on the sensor ID");
        } catch (Exception e) {
            test.fail("Failed to click on the sensor ID: " + e.getMessage());
            throw e;
        }
    }

    @Then("user masuk ke halaman detail sensor")
    public void user_masuk_ke_halaman_detail_sensor() {
        try {
            Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/edit-delete/read-sensor/"));
            test.pass("User is on the sensor detail page");
        } catch (AssertionError e) {
            test.fail("User is not on the sensor detail page: " + e.getMessage());
            throw e;
        }
    }

    @Given("user berada di halaman detail sensor")
    public void user_berada_di_halaman_detail_sensor() {
        try {
            user_berada_di_halaman_daftar_sensor();
            user_menekan_id_sensor();
            Assertions.assertTrue(driver.getCurrentUrl().contains("/read-sensor"));
            test.pass("User is on the sensor detail page");
        } catch (AssertionError e) {
            test.fail("User is not on the sensor detail page: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan tombol delete sensor")
    public void user_menekan_tombol_delete_sensor() {
        try {
            detailSensorPage.btnDeleteClick();
            test.pass("User clicked the delete sensor button");
        } catch (Exception e) {
            test.fail("Failed to click the delete sensor button: " + e.getMessage());
            throw e;
        }
    }

    @Then("muncul pesan pada halaman daftar sensor")
    public void muncul_pesan_pada_halaman_delete_sensor() {
        try {
            boolean isMessageDisplayed = detailSensorPage.isMessageDisplayed();
            Assertions.assertTrue(isMessageDisplayed, "Message should be displayed");
            test.pass("Message is displayed on the daftar sensor page");
        } catch (AssertionError e) {
            test.fail("Message is not displayed on the daftar sensor page: " + e.getMessage());
            throw e;
        }
    }
}
