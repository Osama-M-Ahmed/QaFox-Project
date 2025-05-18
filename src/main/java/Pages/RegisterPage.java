package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterPage extends PageBase {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By firstName = By.xpath("//*[@id=\"input-firstname\"]");
    private final By lastName = By.xpath("//*[@id=\"input-lastname\"]");
    private final By email = By.xpath("//*[@id=\"input-email\"]");
    private final By telephone = By.xpath("//*[@id=\"input-telephone\"]");
    private final By password = By.xpath("//*[@id=\"input-password\"]");
    private final By confirmPassword = By.xpath("//*[@id=\"input-confirm\"]");
    private final By subscribe = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input");
    private final By dontSubscribe = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input");
    private final By privacyPolicy = By.xpath("//*[@type=\"checkbox\"]");
    private final By continueButton = By.xpath("//*[@type=\"submit\"]");
    private final By successMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");

    // Actions
    public void enterFirstName(String firstname) {
        enter(firstName, firstname);
    }

    public void enterLastName(String lastname) {
        enter(lastName, lastname);
    }

    public void enterEmail(String email) {
        enter(this.email, email);
    }

    public void enterTelephone(String telephone) {
        enter(this.telephone, telephone);
    }

    public void enterPassword(String password) {
        enter(this.password, password);
    }

    public void confirmPassword(String confirmPassword) {
        enter(this.confirmPassword, confirmPassword);
    }

    public void subscribe(String text) {
        if (text.equalsIgnoreCase("yes")) {
            click(subscribe);
        } else if (text.equalsIgnoreCase("no")) {
            click(dontSubscribe);
        }
    }

    public void agreePrivacyPolicy() {
        click(privacyPolicy);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void registerANewUser(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterTelephone(telephone);
        enterPassword(password);
        confirmPassword(confirmPassword);
        subscribe("yes");
        agreePrivacyPolicy();
        clickContinue();
    }

    public void registerANewUserWithoutAgreePrivacyPolicy(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterTelephone(telephone);
        enterPassword(password);
        confirmPassword(confirmPassword);
        subscribe("yes");
        clickContinue();
    }

    public String getSuccessMessageText() {
        return driver.findElement(successMessage).getText();
    }


    public String getFirstNameErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='First Name must be between 1 and 32 characters!']")).getText();
    }

    public String getLastNameErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='Last Name must be between 1 and 32 characters!']")).getText();
    }

    public String getEmailErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='E-Mail Address does not appear to be valid!']")).getText();
    }

    public String getTelephoneErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='Telephone must be between 3 and 32 characters!']")).getText();
    }

    public String getPasswordErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='Password must be between 4 and 20 characters!']")).getText();
    }

    public String getConfirmPasswordErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='Password confirmation does not match password!']")).getText();
    }

    public String getEmailAlreadyRegisteredErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='Warning: E-Mail Address is already registered!']")).getText();
    }

    public String getPrivacyPolicyErrorMessage() {
        return driver.findElement(By.xpath("//*[text()='Warning: You must agree to the Privacy Policy!']")).getText();
    }

    public String getRelevantErrorMessage(String key) {
        switch (key) {
            case "register.firstName.required":
                return getFirstNameErrorMessage();
            case "register.lastName.required":
                return getLastNameErrorMessage();
            case "register.email.required":
                return getEmailErrorMessage();
            case "register.telephone.required":
                return getTelephoneErrorMessage();
            case "register.password.required":
                return getPasswordErrorMessage();
            case "register.confirmPassword.required":
                return getConfirmPasswordErrorMessage();
            case "register.email.alreadyRegistered":
                return getEmailAlreadyRegisteredErrorMessage();
            case "register.privacyPolicy.required":
                return getPrivacyPolicyErrorMessage();
            default:
                return "Unknown error key!";
        }
    }

    // Assertions
    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public void validateErrorMessageWhenFirstNameFieldLeftBlank() {
        String expectedMessage = "First Name must be between 1 and 32 characters!";
        String actualMessage = getFirstNameErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }

    public void validateErrorMessageWhenLastNameFieldLeftBlank() {
        String expectedMessage = "Last Name must be between 1 and 32 characters!";
        String actualMessage = getLastNameErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }

    public void validateErrorMessageWhenEmailFieldLeftBlank() {
        String expectedMessage = "E-Mail Address does not appear to be valid!";
        String actualMessage = getEmailErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }

    public void validateErrorMessageWhenTelephoneFieldLeftBlank() {
        String expectedMessage = "Telephone must be between 3 and 32 characters!";
        String actualMessage = getTelephoneErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }

    public void validateErrorMessageWhenPasswordFieldLeftBlank() {
        String expectedMessage = "Password must be between 4 and 20 characters!";
        String actualMessage = getPasswordErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }

    public void validateErrorMessageWhenConfirmPasswordFieldLeftBlank() {
        String expectedMessage = "Password confirmation does not match password!";
        String actualMessage = getConfirmPasswordErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }

    public void validateErrorMessageWhenEmailAlreadyRegistered() {
        String expectedMessage = "Warning: E-Mail Address is already registered!";
        String actualMessage = getEmailAlreadyRegisteredErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }

    public void validateErrorMessageWhenPrivacyPolicyNotAgreed() {
        String expectedMessage = "Warning: You must agree to the Privacy Policy!";
        String actualMessage = getPrivacyPolicyErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The error message is not displayed or is incorrect.");
    }


}