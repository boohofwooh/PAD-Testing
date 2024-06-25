package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DaftarSensorPage {
    private By tabelSensor = By.xpath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table");
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
}
