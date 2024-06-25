package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DaftarSensorPage {
    private By addSensor = new By.ByXPath("//*[@id=\"openModal\"]");
    private By tabelSensor = By.xpath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table");
    private By dropdown = new  By.ByXPath("//*[@id=\"id_lahan\"]/option[3]");
    private By searchField = new By.ByXPath("/html/body/div/div[2]/main/div[1]/div[2]/div[1]/div[2]/div/form/input[2]");
    private By searchButton = new By.ByXPath("/html/body/div/div[2]/main/div[1]/div[2]/div[1]/div[2]/div/form/button");
    private By sensorId = new By.ByXPath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[1]/form/button");
    WebDriver driver;

    public DaftarSensorPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isDaftarSensorDisplayed(){
        try {
            WebElement tableElement = driver.findElement(tabelSensor);
            return tableElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void addSensorClicked(){
        driver.findElement(addSensor).click();
    }

    public void searchSensor(String id) {
        driver.findElement(searchField).sendKeys(id);
        driver.findElement(searchButton).submit();
    }

    public void sensorDetail(){
        driver.findElement(sensorId).submit();
    }

}
