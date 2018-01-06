package utils.waits;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CustomWait {

    private static final int DEFAULT_TIMEOUT_IN_SEC = 20;
    private static final int PULLING_TIMEOUT_IN_SEC = 5;

    WebDriver driver;

    public CustomWait(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForDocumentDisappear(List<WebElement> listOfDocuments){
        FluentWait wait = new FluentWait(driver).withTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS).pollingEvery(PULLING_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        wait.until((ExpectedCondition) driver -> (listOfDocuments.size() < 4));
    }
}
