package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Hooks {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    @Before
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            extent = ExtentReportManager.getInstance();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (extent != null) {
            extent.flush();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }

    public static ExtentTest getTest() {
        return test;
    }
}
