package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends PageBase {
    WebDriver wait;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    By email = By.xpath("//*[@id='input-email']");
    By password = By.xpath("//*[@id='input-password']");
    By forgottenPassword = By.xpath("//*[text()='Forgotten Password']");
    By loginButton = By.xpath("//*[@value='Login']");
    By errorMessage = By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']");


    //Actions
    public void enterEmail(String email) {
        enter(this.email, email);
    }

    public void enterPassword(String password) {
        enter(this.password, password);
    }

    public void clickForgottenPassword() {
        click(forgottenPassword);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }


    public String getSuccessfulLoginMessage() {
        return driver.findElement(By.xpath("//*[text()='My Orders']")).getText();
    }

    public String getErrorMessages() {
        return driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
    }

    public String getErrorMessageWhenEmailIsEmpty() {
        return driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
    }

    public String getErrorMessageWhenPasswordIsEmpty() {
        return driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
    }

    public String getErrorMessageWhenEmailAndPasswordAreEmpty() {
        return driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
    }

    public String getErrorMessageWhenEmailIsInvalid() {
        return driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
    }

    public String getErrorMessageWhenPasswordIsInvalid() {
        return driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
    }



    //Asserions

    public void assertSuccessfulLoginMessage() {
        String expectedMessage = getSuccessfulLoginMessage();
        String actualMessage = getSuccessfulLoginMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The success message is not displayed or is incorrect.");
    }

    public void assertErrorMessages() {
        String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
        String actualMessage = getErrorMessages();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }


    public void assertLoginErrorMessage() {
        String actualMessage = driver.findElement(errorMessage).getText();
        boolean isInvalidCredentials = actualMessage.contains("Warning: No match for E-Mail Address and/or Password.");
        boolean isAccountLocked = actualMessage.contains("Your account has exceeded allowed number of login attempts");

        Assert.assertTrue(isInvalidCredentials || isAccountLocked,
                "Unexpected login error message: " + actualMessage);
    }

}
