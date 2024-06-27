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
import page.DashboardAdminPage;
import page.LoginPage;

public class LogoutAdminSteps {
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage;
    DashboardAdminPage dashboardAdminPage;
    ExtentReports extent = Hooks.getExtent();
    ExtentTest test;

    public LogoutAdminSteps() {
        loginPage = new LoginPage(driver);
        dashboardAdminPage = new DashboardAdminPage(driver);
        test = extent.createTest("Logout Admin Test");
        Hooks.setTest(test);
    }
    @Given("user merupakan admin")
    public void user_merupakan_admin() {
        try {
            test.info("User attempting to log in as admin");
            driver.get("http://127.0.0.1:8000/login"); // Replace with your website URL
            loginPage.fillEmail("AdminSmf@gmail.com");
            loginPage.fillPassword("12345678");
            loginPage.clickLogin();
            test.pass("User logged in as admin");
        } catch (Exception e) {
            test.fail("Failed to log in as admin: " + e.getMessage());
            throw e;
        }
    }

    @When("user menekan tombol akun pada sidebar")
    public void user_menekan_tombol_akun_pada_sidebar() {
        try {
            test.info("User clicks on account button in sidebar");
            dashboardAdminPage.accountBtnClick();
            test.pass("Account button clicked");
        } catch (Exception e) {
            test.fail("Failed to click on account button: " + e.getMessage());
            throw e;
        }
    }

    @And("user menekan logout")
    public void user_menekan_logout() {
        try {
            test.info("User clicks on logout button");
            dashboardAdminPage.logoutClick();
            test.pass("Logout button clicked");
        } catch (Exception e) {
            test.fail("Failed to click on logout button: " + e.getMessage());
            throw e;
        }
    }

    @Then("user kembali ke halaman login")
    public void user_kembali_ke_halaman_login() {
        try {
            test.info("Verifying user is redirected to login page");
            Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
            test.pass("User is redirected to login page");
        } catch (AssertionError e) {
            test.fail("User is not redirected to login page: " + e.getMessage());
            throw e;
        }
    }
}
