package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailLahanPage {
    private By btnDelete = new By.ByXPath("/html/body/div/div[2]/main/div/div/div[2]/div[5]/form[1]/button");
    private By message = new By.ByXPath("//*[@id=\"swal2-title\"]");
    WebDriver driver;

    public DetailLahanPage(WebDriver driver){
        this.driver = driver;
    }
    public void btnDeleteClick(){
        driver.findElement(btnDelete).submit();
    }

    public boolean isMessageDisplayed(){
        try {
            WebElement tableElement = driver.findElement(message);
            return tableElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
