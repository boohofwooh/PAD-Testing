package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DaftarFarmerPage {
    private By tabelDaftarFarmer = new By.ByXPath("/html/body/div/div[2]/main/div/div[2]/div[2]/table");
    private By searchField = new By.ByXPath("/html/body/div/div[2]/main/div/div[2]/div[1]/div[2]/div/form/input[2]");
    private By searchButton = new By.ByXPath("/html/body/div/div[2]/main/div/div[2]/div[1]/div[2]/div/form/button");
    private By addButton = new By.ByXPath("//*[@id=\"openModal\"]");
    private By nameModal = new By.ByXPath("//*[@id=\"name\"]");
    private By emailModal = new By.ByXPath("//*[@id=\"email\"]");
    private By passwordModal = new By.ByXPath("//*[@id=\"password\"]");
    private By addressModal = new By.ByXPath("//*[@id=\"alamat_user\"]");
    private By saveButton = new By.ByXPath("//*[@id=\"modal\"]/div/form/div[5]/button[2]");
    private By farmerName = new By.ByXPath("/html/body/div/div[2]/main/div/div[2]/div[2]/table/tbody/tr[1]/td[2]/form/button");


    WebDriver driver;

    public DaftarFarmerPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isDaftarFarmerDisplayed(){
        try {
            WebElement tableElement = driver.findElement(tabelDaftarFarmer);
            return tableElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void searchFarmerByName(String name) {
        driver.findElement(searchField).sendKeys(name);
        driver.findElement(searchButton).submit();
    }

    public void addButtonClick(){
        driver.findElement(addButton).click();
    }

    public void modalFill(String name, String email, String password, String address) {
        driver.findElement(nameModal).sendKeys(name);
        driver.findElement(emailModal).sendKeys(email);
        driver.findElement(passwordModal).sendKeys(password);
        driver.findElement(addressModal).sendKeys(address);
        driver.findElement(saveButton).submit();
    }

    public void farmerDetail(){
        driver.findElement(farmerName).submit();
    }
}
