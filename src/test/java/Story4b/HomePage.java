package Story4b;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {
    @FindBy(id = "userid")
    private WebElement useridField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "logIn")
    private WebElement logInButton;

    public HomePage(WebDriver driver) {
        super(driver);
        //command is niet voor iedereen hetzelfde
        this.driver.get(getPath()+"?command=Index");
    }

    public boolean containsWelkomText(String welkomTekst) {
        String foundName = this.driver.findElement(By.cssSelector("h2")).getText();
        boolean found = false;
        if(foundName.equals(welkomTekst)) {
            found=true;
        }
        return found;
    }

    public void logIn(String userid, String password) {
        useridField.sendKeys(userid);
        passwordField.sendKeys(password);
        logInButton.click();
    }
}
