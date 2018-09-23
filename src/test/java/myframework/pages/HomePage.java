package myframework.pages;

import myframework.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement loginLabel;

    private WebDriver driver;
    private Waits waits;

    public HomePage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTextFromLoginLabel() {
        waits.waitForElementToBeVisible(loginLabel);
        return loginLabel.getText();
    }
}
