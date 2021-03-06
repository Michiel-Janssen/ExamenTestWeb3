import Story4b.AddContactPage;
import Story4b.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class popularFitnessTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\School\\WebHulpProgramma's\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Overview");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void shows_contacts_page() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.setUseridField("r0789294");
        loginPage.setPasswordField("t");

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);

        driver.navigate().to("http://localhost:8080/Controller?command=Contacts");

        String title = driver.getTitle();
        assertEquals("Contacts",title);
    }
}
