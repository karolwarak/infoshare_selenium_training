package tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Tasks {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void checkDragTask() {
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");

        WebElement smallBall = driver.findElement(By.id("draggable"));
        WebElement bigBall = driver.findElement(By.id("droptarget"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(smallBall, bigBall).build().perform();

        assertThat(bigBall.getText())
                .as("Ball was not moved.").isEqualTo("You did great!");
    }

    @Test
    public void checkDatePicker() {
        driver.get("https://jqueryui.com/datepicker/#dropdown-month-year");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        // You need wait here as sometimes element is not fully loaded.
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(By.id("datepicker")));

        dateField.click();

        selectFromDropDown("month", "Oct");
        selectFromDropDown("year", "2019");

        WebElement dayOfTheMonth = driver.findElement(By.xpath(String.format("//a[text() = '%s']", "21")));
        dayOfTheMonth.click();

        assertThat(dateField.getAttribute("value"))
                .as("Wrong date.").isEqualTo("10/21/2019");
    }

    private void selectFromDropDown(String dropDownName, String value){
        Select select = new Select(driver.findElement(By.xpath(String.format("//select[@class='ui-datepicker-%s']", dropDownName))));
        select.selectByVisibleText(value);
    }

    @Test
    public void checkMenuItems() {
        driver.get("https://jqueryui.com/demos/");

        List<WebElement> listOfMenuItems = driver.findElements(By.id("menu-top"));

        List<String> listOfTextsFromMenuItems = new ArrayList<>();
        for (WebElement e : listOfMenuItems) listOfTextsFromMenuItems.add(e.getText());

        assertThat(listOfTextsFromMenuItems).contains("Demos\n" +
                "Download\n" +
                "API Documentation\n" +
                "Themes\n" +
                "Development\n" +
                "Support\n" +
                "Blog\n" +
                "About");
    }
}
