package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class ProductPage extends PageBase {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By laptopsNotebooksLink = By.xpath("//a[contains(text(),'Laptops & Notebooks')]");
    private final By showAllLaptopsLink = By.xpath("//*[text()='Show AllLaptops & Notebooks']");
    private final By macBookLink = By.xpath("//a[contains(text(),'MacBook')]");
    private final By addToCartButton = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div/button");
    private final By successMessage = By.cssSelector(".alert-success");
    private final By cartTotal = By.id("cart-total");
    private final By quantityInput = By.id("input-quantity");
    private final By cartDropdown = By.id("cart");
    private final By couponAccordion = By.xpath("//a[contains(text(),'Use Coupon Code')]");
    private final By couponInput = By.id("input-coupon");
    private final By applyCouponButton = By.id("button-coupon");
    private final By errorMessage = By.cssSelector(".alert-danger");
    private final By checkoutLink = By.xpath("//a[contains(text(),'Checkout')]");
    private final By viewCartLink = By.xpath("//strong[contains(text(),'View Cart')]");
    private final By emptyCart = By.xpath("//*[@class='text-center']");

    // Navigation Methods
    public void navigateToMacBook() {
        click(laptopsNotebooksLink);
        click(showAllLaptopsLink);
        click(macBookLink);
    }

    // Cart Actions
    public void setQuantity(String quantity) {
        driver.findElement(quantityInput).clear();
        enter(quantityInput, quantity);
    }

    public void addToCart() {
//        waitForElementToBeClickable(addToCartButton,10);
        click(addToCartButton);
        waitForSuccessMessage();
    }

    public void openCart() {
        click(cartDropdown);
    }

    public void viewCart() {
        openCart();
        click(viewCartLink);
    }

    // Coupon Actions
    public void applyCoupon(String code) {
        click(couponAccordion);
        enter(couponInput, code);
        click(applyCouponButton);
    }

    // Get Messages/Status
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(errorMessage),
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        ));
        if (driver.findElements(errorMessage).size() > 0) {
            return driver.findElement(errorMessage).getText();
        } else if (driver.findElements(successMessage).size() > 0) {
            return driver.findElement(successMessage).getText();
        }
        return "";
    }

    public String getEmptyCartMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCart));
        return driver.findElement(emptyCart).getText();
    }

    public String getCartTotal() {
        return driver.findElement(cartTotal).getText();
    }

    private void waitForSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    // Validations
    public void verifyProductAddedSuccessfully(String expectedText) {
        waitUntilVisible(successMessage);
        String message = getSuccessMessage();
        Assert.assertTrue(message.contains(expectedText),
                "Product was not added to cart successfully. Expected: " + expectedText + ", Got: " + message);
    }

    public void verifyCartTotalItems(String expectedCount) {
        String total = getCartTotal();
        Assert.assertTrue(total.contains(expectedCount),
                "Cart total does not show correct item count. Expected: " + expectedCount + ", Got: " + total);
    }

    public void verifyErrorMessage(String expectedError) {
        String message = getErrorMessage();
        Assert.assertTrue(message.contains(expectedError),
                "Expected error message: " + expectedError + ", but got: " + message);
    }

    public void verifyUserCannotAddCouponWhenCartEmpty(String emptyCart) {
        String message = getEmptyCartMessage();
        Assert.assertTrue(message.contains(emptyCart),
                "Expected error message: " + emptyCart + ", but got: " + message);
    }
}

