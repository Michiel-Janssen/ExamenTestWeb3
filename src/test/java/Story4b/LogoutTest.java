package Story4b;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoutTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\WebHulpProgramma's\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_All_Fields_Are_Filled_In_Correct_Logs_In_And_Then_Out() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.setUseridField("r0789294");
        loginPage.setPasswordField("t");

        HomePage homePage = loginPage.submitValid();

        assertEquals("Home", homePage.getTitle());

        AccountPageUser accountPageUser = PageFactory.initElements(driver, AccountPageUser.class);
        accountPageUser.hasLogOutButton();
        accountPageUser.clickLogOut();
        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.homePageContainsLogOutMessage("Succesvol uitgelogd"));
    }
}
