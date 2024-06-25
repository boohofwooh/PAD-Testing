package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailFarmerPage {
    private By deleteButton = new By.ByXPath("/html/body/div/div[2]/main/div/div[2]/div[1]/div[2]/form[1]/button");
    private By message = new By.ByXPath("//*[@id=\"swal2-title\"]");
    private By editButton = new By.ByXPath("/html/body/div/div[2]/main/div/div[2]/div[1]/div[2]/form[2]");
    private By formName = new By.ByXPath("//*[@id=\"name\"]");
    private By formEmail = new By.ByXPath("//*[@id=\"email\"]");
    private By formPassword = new By.ByXPath("//*[@id=\"password\"]");
    private By formAlamat = new By.ByXPath("//*[@id=\"alamat_user\"]");

    private By saveBtn = new By.ByXPath("/html/body/div/div[2]/main/div/div[2]/div[1]/div[2]/button");

    WebDriver driver;

    public DetailFarmerPage(WebDriver driver){
        this.driver = driver;
    }

    public void deleteClick(){
        driver.findElement(deleteButton).submit();
    }

    public boolean isMessageDisplayed(){
        try {
            WebElement tableElement = driver.findElement(message);
            return tableElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void editClicked(){
        driver.findElement(editButton).submit();
    }

    public void editUser(String name, String email, String password, String address){
        driver.findElement(formName).clear();
        driver.findElement(formName).sendKeys(name);

        driver.findElement(formEmail).clear();
        driver.findElement(formEmail).sendKeys(email);

        driver.findElement(formPassword).clear();
        driver.findElement(formPassword).sendKeys(password);

        driver.findElement(formAlamat).clear();
        driver.findElement(formAlamat).sendKeys(address);

    }

    public void saveBtnClicked(){
        driver.findElement(saveBtn).click();
    }
}
