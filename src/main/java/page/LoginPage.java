package page;

import object.LoginObject;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    LoginObject loginObject;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        loginObject = new LoginObject(driver);
    }
    public void fillEmail(String email){
        driver.findElement(loginObject.getInputEmail()).sendKeys(email);
    }
    public void fillPassword(String password){
        driver.findElement(loginObject.getInputPassword()).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(loginObject.getLoginButton()).click();
    }
}
