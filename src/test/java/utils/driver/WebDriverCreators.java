package utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverCreators {

    public static final WebDriverCreator FIREFOX_GECKO = new WebDriverCreator() {
        public WebDriver create() {
            startGeckoDriverServer();
            WebDriver driver = new FirefoxDriver();
            return driver;
        }
    };

    public static final WebDriverCreator CHROME = new WebDriverCreator() {
        public WebDriver create() {
            startChromeDriverServer();
            WebDriver driver = new ChromeDriver();
            return driver;
        }
    };

    private static void startGeckoDriverServer() {
        System.setProperty("webdriver.firefox.marionette", GeckoDriverExecutor.getProperDriverExecutable());
    }

    private static void startChromeDriverServer() {
        System.setProperty("webdriver.chrome.driver", ChromeDriverExecutor.getProperDriverExecutable());
    }

}

