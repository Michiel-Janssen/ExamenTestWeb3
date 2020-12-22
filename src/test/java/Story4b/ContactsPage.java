package Story4b;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * @author gerben
 * @author Mats
 */
public class ContactsPage extends Page {
    public ContactsPage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Contacts");
    }
    public boolean containsBezoek(String naam,String email,String fitness){
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(naam) &&  listItem.getText().contains(email) &&  listItem.getText().contains(fitness)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsName(String fullName) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(fullName)) {
                found=true;
            }
        }
        return found;
    }
}
