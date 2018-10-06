package ddt;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;
import utils.waits.CustomWait;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class DDTTask {

    private static final String PAGE_URL = "http://bmi-online.pl/";

    private WebDriver driver;
    private CustomWait customWait;

    @DataProvider
    public static Object[][] testDataForBMI() {
        return new String[][]{
                new String[]{"M", "70", "170", "wagę prawidłową"},
                new String[]{"M", "45", "170", "wygłodzenie"},
                new String[]{"K", "70", "170", "wagę prawidłową"},
                new String[]{"K", "100", "170", "I stopień otyłości"},
        };
    }

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();
        driver.get(PAGE_URL);
        customWait = new CustomWait(driver);

    }

    @Test
    @UseDataProvider("testDataForBMI")
    public void checkBMI(String sex, String weight, String height, String result) {

        WebElement rodoPopUpButton = driver.findElement(By.xpath("//span[@class='t-a-c__box__btn__label']"));
        customWait.waitForElementToBeVisible(rodoPopUpButton);
        rodoPopUpButton.click();

        WebElement femaleButton = driver.findElement(By.xpath("//label[@for='bmi_gender_f']"));
        WebElement maleButton = driver.findElement(By.xpath("//label[@for='bmi_gender_m']"));
        WebElement weightField = driver.findElement(By.xpath("//input[@name='weight']"));
        WebElement heightField = driver.findElement(By.xpath("//input[@name='height']"));
        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit']"));

        WebElement overlayElementFromRodo = driver.findElement(By.xpath("//div[@class='t-a-c__overlay']"));
        customWait.waitForElementToBeClickable(overlayElementFromRodo);

        if (sex.equalsIgnoreCase("K")) {
            customWait.waitForElementToBeClickable(femaleButton);
            if(!femaleButton.isSelected())
            femaleButton.click();
        }

        if (sex.equalsIgnoreCase("M")) {
            customWait.waitForElementToBeClickable(maleButton);
            if(!maleButton.isSelected())
            maleButton.click();
        }

        weightField.sendKeys(weight);
        heightField.sendKeys(height);
        calculateButton.click();

        WebElement resultLabel = driver.findElement(By.xpath("//span[@class='result-v1__title-des']"));
        customWait.waitForElementToBeVisible(resultLabel);
        String textFromLabel = resultLabel.getText();

        assertThat(textFromLabel).contains(result).as("BMI result label contains wrong value.");
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
