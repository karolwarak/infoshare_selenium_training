package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.WebDriverCreators;
import utils.WebDriverProvider;

public class Module1_Locators {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openBrowser() {
        driver.get("http://google.com");
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
