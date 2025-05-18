package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public Select select;
    public Actions action;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait time of 10 seconds
    }

    public void navigateToWebsite() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
    }

    public void fillText(WebElement el, String text) {
        waitForElementToBeClickable(el);
        highlightElement(el, "yellow");
        el.clear();
        el.sendKeys(text);
    }
    protected void waitUntilVisible(By locator) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(WebElement el) {
        wait.until(ExpectedConditions.elementToBeClickable(el));
    }

    public void waitForElementToBeVisible(WebElement el) {
        wait.until(ExpectedConditions.visibilityOf(el));
    }


    protected void enter(By locator, String text) {

        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {

        driver.findElement(locator).click();
    }

    protected void waitFor(int seconds) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void waitForTextToAppear(String expectedText, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), expectedText));
    }


    /*
     * Call this method with your element and a color like (red,green,orange etc...)
     */
    protected void highlightElement(WebElement element, String color) {
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "background-color:" + color + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few milliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);

    }


}



