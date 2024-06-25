package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DaftarLahanPage {
    private By tabelLahan = new By.ByXPath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table");
    private By addLahanBtn = new By.ByXPath("//*[@id=\"openModal\"]");
    private By searchField = new By.ByXPath("/html/body/div/div[2]/main/div[1]/div[2]/div[1]/div[2]/div/form/input[2]");
    private By searchButton = new By.ByXPath("/html/body/div/div[2]/main/div[1]/div[2]/div[1]/div[2]/div/form/button");
    private By lahanId = new By.ByXPath("/html/body/div/div[2]/main/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[1]/form");
    WebDriver driver;

    public DaftarLahanPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isDaftarLahanDisplayed(){
        try {
            WebElement tableElement = driver.findElement(tabelLahan);
            return tableElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void addLahanBtnClick(){
        driver.findElement(addLahanBtn).click();
    }

    public void searchLahan(String id) {
        driver.findElement(searchField).sendKeys(id);
        driver.findElement(searchButton).submit();
    }

    public void lahanDetail(){
        driver.findElement(lahanId).submit();
    }

}
