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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    String currentUserEmail;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        test = extent.createTest("Login Test");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    @Given("user berada di halaman login")
    public void user_berada_di_halaman_login() {
        test.info("Navigating to login page");
        driver.get("http://127.0.0.1:8000/login");
        test.pass("Navigated to login page successfully");
    }

    @When("user memasukkan email {string} dan password {string} yang valid")
    public void admin_memasukkan_email_dan_password_yang_valid(String email, String password) {
        test.info("Entering email and password");
        currentUserEmail = email;
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        test.pass("Entered email: " + email + " and password: " + password);
    }

    @And("user menekan tombol login")
    public void user_menekan_tombol_login() {
        test.info("Clicking login button");
        loginPage.clickLogin();
        test.pass("Login button clicked");
    }

    @Then("user harus diarahkan ke dashboard")
    public void user_harus_diarahkan_ke_dashboard() {
        test.info("Verifying redirection to dashboard");
        try {
            if (currentUserEmail.equals("AdminSmf@gmail.com")) {
                Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/dashboard/admin-dashboard"));
                test.pass("Admin redirected to admin dashboard successfully");
            } else if (currentUserEmail.equals("diego@gmail.com")) {
                Assertions.assertTrue(driver.getCurrentUrl().contains("/pages/dashboard/user-dashboard"));
                test.pass("User redirected to user dashboard successfully");
            } else {
                test.fail("Unknown user email");
                Assertions.fail("Unknown user email");
            }
        } catch (AssertionError e) {
            test.fail("Redirection to dashboard failed: " + e.getMessage());
            throw e;
        }
    }
}
