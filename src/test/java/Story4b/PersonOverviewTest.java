package Story4b;

import domain.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

/**
 * author Baljit Singh & Mathias Devos
 */

public class PersonOverviewTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\WebHulpProgramma's\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void Test_Logged_In_As_Admin_Shows_OverviewPage() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("r0789294", "t");

        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        //assertEquals("Overview", personOverviewPage.getTitle());
        assertEquals("Overview", personOverviewPage.getTitle());
        assertTrue(personOverviewPage.containsUserWithEmail("admin@hotmail.com"));
    }

    @Test
    public void Test_Logged_Out_User_Cannot_See_OverviewPage() {
        //geeft als return de homePage aangezien we als een bezoeker geen rechten hebben om deze pagina te bekijken
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Home", personOverviewPage.getTitle());
    }

    @After
    public void clean() {
        driver.quit();
    }
}
