package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    String currentUserEmail;
    ExtentReports extent;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extent.flush();
        }
    }

    @Given("user berada di halaman login")
    public void user_berada_di_halaman_login() {
        driver.get("http://127.0.0.1:8000/login");
    }

    @When("user memasukkan email {string} dan password {string} yang valid")
    public void admin_memasukkan_email_dan_password_yang_valid(String email, String password) {
        currentUserEmail = email;
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
    }

    @And("user menekan tombol login")
    public void user_menekan_tombol_login() {
        loginPage.clickLogin();
    }

    @Then("user harus diarahkan ke dashboard")
    public void user_harus_diarahkan_ke_dashboard() {
        if (currentUserEmail.equals("AdminSmf@gmail.com")) {
            Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/dashboard/admin-dashboard"));
        } else if (currentUserEmail.equals("diego@gmail.com")) {
            Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/dashboard/user-dashboard"));
        } else {
            Assertions.fail("Unknown user email");
        }
    }
}