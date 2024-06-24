package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardAdminPage {
    private By sidebarDashboard = new By.ByXPath("//*[@id=\"sidebar\"]/div[2]/div/ul/li[1]/a");
    private By sidebarDaftarFarmer = new By.ByXPath("//*[@id=\"sidebar\"]/div[2]/div/ul/li[2]/a");
    private By sidebarDaftarLahan = new By.ByXPath("//*[@id=\"sidebar\"]/div[2]/div/ul/li[3]/a");
    private By sidebarDaftarSensor = new By.ByXPath("//*[@id=\"sidebar\"]/div[2]/div/ul/li[4]/a");

    WebDriver driver;

    public DashboardAdminPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickSidebarDashboard(){
        WebElement element = driver.findElement(sidebarDashboard);
        element.click();
    }

    public void clickSidebarDaftarFarmer(){
        WebElement element = driver.findElement(sidebarDaftarFarmer);
        element.click();
    }

    public void clickSidebarDaftarLahan(){
        WebElement element = driver.findElement(sidebarDaftarLahan);
        element.click();
    }

    public void clickSidebarDaftarSensor(){
        WebElement element = driver.findElement(sidebarDaftarSensor);
        element.click();
    }
}
