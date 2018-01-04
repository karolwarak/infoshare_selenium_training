package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverCreators;
import utils.WebDriverProvider;

import static org.junit.Assert.*;

public class Module2_SeleniumFeatures {

    private final static String PAGE_HEADER_TEXT = "Selenium: Beginners Guide";

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyTextOnPage() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement pageHeader = driver.findElement(By.xpath("//div[@class= 'mainheading']"));

        // This is a good practice to add negative message showing reason why test fails.
        assertEquals("Page header is incorrect.", PAGE_HEADER_TEXT, pageHeader.getText());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
