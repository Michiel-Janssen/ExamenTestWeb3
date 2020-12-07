package Story4b;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
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
}
