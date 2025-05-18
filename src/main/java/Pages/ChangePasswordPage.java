package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ChangePasswordPage extends PageBase {

    //Constructor
    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    By changePassword = By.xpath("//*[text()='Change your password']");
    By password = By.xpath("//*[@id=\"input-password\"]");
    By confirmPassword = By.xpath("//*[@id=\"input-confirm\"]");
    By continueButton = By.xpath("//*[@type=\"submit\"]");
    By backButton = By.xpath("//*[@id=\"content\"]/form/div/div[1]/a");
    By successMessage = By.xpath("//*[text()='Success: Your password has been successfully updated.']");
    By errorMessage = By.xpath("//*[text()='Cannot change to the same password']");


    //Actions

    public void enterPassword(String password) {
        enter(this.password, password);
    }

    public void confirmPassword(String confirmPassword) {
        enter(this.confirmPassword, confirmPassword);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

    public void clickBackButton() {
        click(backButton);
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickChangePassword() {
        click(changePassword);
    }

    public void changePasswordToNewPassword(String password) {
        clickChangePassword();
        enterPassword(password);
        confirmPassword(password);
        clickContinueButton();
    }

    public void changePasswordToSamePassword(String password) {
        clickChangePassword();
        enterPassword(password);
        confirmPassword(password);
        clickContinueButton();
    }


    //Assertions

    public void assertSuccessMessageWhenChangeToNewPassword() {
        String expectedMessage = "Success: Your password has been successfully updated.";
        String actualMessage = getSuccessMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Success message is not displayed as expected.");
    }

    public void assertErrorMessageWhenChangeToSamePassword() {
        String actualMessage = getSuccessMessage();
        Assert.assertFalse(actualMessage.contains("Success"),
                "User was able to change password to the same one");
    }


}
