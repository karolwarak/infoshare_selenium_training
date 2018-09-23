package myframework.pages;

import myframework.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement loginLabel;

    private WebDriver driver;
    private Waits waits;

    public HomePage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(driver);

        // Remember to use this! If you use HomePage.class you will get memory leak!
        PageFactory.initElements(driver, this);
    }

    public String getTextFromLoginLabel() {
        waits.waitForElementToBeVisible(loginLabel);
        return loginLabel.getText();
    }
}
