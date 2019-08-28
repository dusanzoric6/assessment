package util;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {


    public static void waitForElementToBeClickable(WebDriver driver, By searchCriteria, int seconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, seconds);
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(searchCriteria));
        } catch (StaleElementReferenceException exception) {
            System.out.println("\n element is clickable");
        }
    }

    public static void waitForElementToBeVisible(WebDriver driver, By searchCriteria, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchCriteria));
    }
}
