package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.*;

public class ManagementSensorSteps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardAdminPage dashboardAdminPage;
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
        daftarSensorPage = new DaftarSensorPage(driver);
        test = extent.createTest("Management Sensor Test");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
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
    public void tabel_daftar_sensor_ditampilkan_di_halaman_daftar_sensor(){
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
}
