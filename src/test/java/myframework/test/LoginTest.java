package myframework.test;

import myframework.pages.HomePage;
import myframework.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void loginUserTest() {
        driver.get("https://www.phptravels.net/login");
        loginPage.loginToThePage("user@phptravels.com", "demouser");

        assertThat(homePage.getTextFromLoginLabel()).as("User is not logged in.").isEqualTo("Hi, Johny Smith");
    }

}
