package Story4b;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddContactTest {

    private WebDriver driver;
    private String path = "http://localhost:8080/Project_war_exploded/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\School\\WebHulpProgramma's\\chromedriver.exe");
        driver = new ChromeDriver();
    }
/*
    @After
    public void clean() {
        driver.quit();
    }


 */
    @Test
    public void test_AddContact_AllFieldsFilledInCorrectly_ContactIsAdded() {
        //Login as admin
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("u0000001", "t");

        //Create a contact
        int randomId = (int) (Math.random()*100);
        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName(randomId+"Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("2020-11-27");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        ContactsPage overview = PageFactory.initElements(driver, ContactsPage.class);
        assertTrue(overview.containsName(randomId + "Jan Janssens"));
    }

    @Test
    public void test_AddContact_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("u0000001", "t");

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No first name given"));
        assertTrue(addContactPage.hasEmptyFirstName());
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyDate("1010-10-10"));
        assertTrue(addContactPage.hasStickyHour("22:14"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("u0000001", "t");

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No last name given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasEmptyLastName());
        assertTrue(addContactPage.hasStickyDate("1010-10-10"));
        assertTrue(addContactPage.hasStickyHour("22:14"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_DateNotFilledIn_ErrorMessageGivenForDateAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("u0000001", "t");

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No valid date or time"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasEmptyDate());
        assertTrue(addContactPage.hasStickyHour("22:14"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_HourNotFilledIn_ErrorMessageGivenForHourAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("u0000001", "t");

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("Email not valid or time not valid"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyDate("1010-10-10"));
        assertTrue(addContactPage.hasEmptyHour());
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_GSMNotFilledIn_ErrorMessageGivenForGSMAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("u0000001", "t");

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("10-10-1010");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("Phone number isn't valid"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyDate("1010-10-10"));
        assertTrue(addContactPage.hasStickyHour("22:14"));
        assertTrue(addContactPage.hasEmptyGsm());
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logIn("u0000001", "t");

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setDate("1010-10-10");
        addContactPage.setHour("22:14");
        addContactPage.setGsm("0468235671");
        addContactPage.setEmail("");
        addContactPage.pressButton();

        assertEquals("Contacts", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No email given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyDate("1010-10-10"));
        assertTrue(addContactPage.hasStickyHour("22:14"));
        assertTrue(addContactPage.hasStickyGsm("0468235671"));
        assertTrue(addContactPage.hasEmptyEmail());
    }
}