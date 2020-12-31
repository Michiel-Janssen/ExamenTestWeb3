package Story4b;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPageUser extends Page {
    public AccountPageUser(WebDriver driver) {
        super(driver);
        this.driver.get(path + "?command=Index");
    }

    @FindBy(id="logOut")
    private WebElement logOutButton;

    public boolean hasLogOutButton() {
        return driver.findElement(By.id("logOut")) != null;
    }

    public void clickLogOut() {
        logOutButton.click();
    }
}
