package Story4b;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Lucas Hendrickx
//Michiel Janssen

public class LoginPage extends Page {

    @FindBy(id="userid")
    private WebElement useridField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="logIn")
    private WebElement logInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Login");
    }

    public void setUseridField(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage submitValid() {
        logInButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void submitInvalid() {
        logInButton.click();
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMessage = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMessage.getText()));
    }

    public boolean hasStickyUserid(String userid) {
        return userid.equals(useridField.getAttribute("value"));
    }

    public void logIn(String userid, String password) {
        useridField.sendKeys(userid);
        passwordField.sendKeys(password);
        logInButton.click();
    }
}
