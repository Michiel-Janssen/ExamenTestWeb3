package Story4b;

import org.openqa.selenium.WebDriver;

//Lucas Hendrickx
//Michiel Janssen

public abstract class Page {

    WebDriver driver;
    String path = "http://localhost:8080/Controller";

    public Page(WebDriver driver) {this.driver = driver;}

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle () {
        return driver.getTitle();
    }
}
