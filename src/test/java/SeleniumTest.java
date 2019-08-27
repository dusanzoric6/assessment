import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import util.DriverFactory;

public class SeleniumTest {

    WebDriver driver;

//


    @Test
    public void googleCheeseExample() throws Exception {
        driver = new DriverFactory().getDriver();

//    Go to site http://www.expedia.com
        driver.get("http://www.expedia.com");
        driver.manage().window().maximize();

//    Select "Flights"
        driver.findElement(By.id("tab-flight-tab-hp")).click();

//    Type London in "Fly from: city or airport"
        WebElement flyingFromInput = driver.findElement(By.id("flight-origin-hp-flight"));
        flyingFromInput.sendKeys(Keys.CONTROL + "a");
        flyingFromInput.sendKeys("London");
        waitForElementToBeVisible(driver, By.id("typeaheadDataPlain"), 5);

//    Select Heathrow in popup
        driver.findElement(By.linkText("(LHR) Heathrow")).sendKeys(Keys.ENTER);

//    Type Dublin in "Flying to: city or airport"
        WebElement flyingToInput = driver.findElement(By.id("flight-destination-hp-flight"));
        flyingToInput.sendKeys(Keys.CONTROL + "a");
        flyingToInput.sendKeys("Dublin");
        waitForElementToBeVisible(driver, By.id("typeaheadDataPlain"), 5);


//    Select "Dublin Airport (DUB), Ireland" in popup
        driver.findElement(By.linkText("Dublin, Ireland\n(DUB)")).sendKeys(Keys.ENTER);

//    Select Departing: 01/03/2019
        WebElement departingDate = driver.findElement(By.id("flight-departing-hp-flight"));
        departingDate.sendKeys("01/03/2020");

//    Select Departing: 07/03/2019
        WebElement returnDate = driver.findElement(By.id("flight-returning-hp-flight"));
        returnDate.sendKeys(Keys.CONTROL + "a");
        returnDate.sendKeys("07/03/2020");

//    Select 2 adults
        driver.findElement(By.id("traveler-selector-hp-flight")).click();
        driver.findElement(By.xpath("//*[@id=\"traveler-selector-hp-flight\"]/div/ul/li/div/div/div/div[1]/div[4]/button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='2 Adults']")).getText(), "2 Adults");

//    Click Search Button
        driver.findElement(By.xpath("//*[@id=\"gcw-flights-form-hp-flight\"]/div[*]/label/button")).sendKeys(Keys.ENTER);

//    Wait for all flights to be loaded. Find first row excluding the promotional row for “Flight + Hotel”
        waitForElementToBeClickable(driver, By.cssSelector("button[class='btn-secondary btn-action t-select-btn']"), 20);

//    Assert Positive scenario that the price in first row is $94 (or any other price at your time)
        String price = driver.findElement(By.xpath("//*[@id='flightModuleList']/li[1]"))
                .findElement(By.xpath("//*[contains(@data-test-id, 'listing-price-dollars')]")).getText();
        Assert.assertEquals(price, "$94");

//     Assert Negative scenario that the price in first row is not $22.33
        Assert.assertNotEquals(price, "$22");

//     Assert the visible left panel list of “Airlines included” below the list of “Stops”
        Assert.assertTrue(driver.findElement(By.id("airlines")).isDisplayed());

//     Scroll to the bottom of the page and assert the visibility of text “© 2018 Expedia, Inc. All rights reserved.”
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement expadiaLegal = driver.findElement(By.xpath("//div[@class='legal' and contains(text(), '2019 Expedia, Inc. All rights reserved.')]"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", expadiaLegal);
        Assert.assertTrue(expadiaLegal.isDisplayed());

    }

    @AfterTest
    public void closeDriverObjects() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    static void waitForElementToBeClickable(WebDriver driver, By searchCriteria, int seconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, seconds);
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(searchCriteria));
        } catch (StaleElementReferenceException exception) {
            System.out.println("\n element is clickable");
        }
    }

    static void waitForElementToBeVisible(WebDriver driver, By searchCriteria, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchCriteria));
    }
}

