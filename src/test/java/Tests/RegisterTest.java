package Tests;

import Base.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static Utils.Utils.readValue;

@Epic("User Management")
@Feature("Registration")
public class RegisterTest extends TestBase {

    Faker faker = new Faker();
    String firstName = readValue("validFirstName");
    String lastName = readValue("validLastName");
    String telephone = readValue("validTelephone");
    String password = readValue("validPassword");
    String confirmPassword = readValue("validConfirmPassword");

    @Test
    @Story("User registers successfully")
    @Description("Validate that the user can register successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void userCanRegisterSuccessfully(){
        email = faker.internet().emailAddress();
        homePage.openRegisterPage();
        registerPage.registerANewUser(firstName, lastName, email, telephone, password, confirmPassword);
        pageBase.waitForTextToAppear(registerPage.getSuccessMessageText(), 10);
        registerPage.isSuccessMessageDisplayed();
        homePage.loginOut();
    }

    @Test
    @Story("User cannot register with missing first name")
    @Description("Validate that the user cannot register when leaving the first name field blank")
    public void userCannotRegisterWhenFirstNameFieldLeftBlank() {
        String email = faker.internet().emailAddress();
        homePage.openRegisterPage();
        registerPage.registerANewUser("", lastName, email, telephone, password, confirmPassword);
        registerPage.validateErrorMessageWhenFirstNameFieldLeftBlank();
    }

    @Test
    @Story("User cannot register with missing last name")
    @Description("Validate that the user cannot register when leaving the last name field blank")
    public void userCannotRegisterWhenLastNameFieldLeftBlank() {
        String email = faker.internet().emailAddress();
        homePage.openRegisterPage();
        registerPage.registerANewUser(firstName, "", email, telephone, password, confirmPassword);
        registerPage.validateErrorMessageWhenLastNameFieldLeftBlank();
    }

    @Test
    @Story("User cannot register with missing email")
    @Description("Validate that the user cannot register when leaving the email field blank")
    public void userCannotRegisterWhenEmailFieldLeftBlank() {
        homePage.openRegisterPage();
        registerPage.registerANewUser(firstName, lastName, "", telephone, password, confirmPassword);
        registerPage.validateErrorMessageWhenEmailFieldLeftBlank();
    }

    @Test
    @Story("User cannot register with missing telephone")
    @Description("Validate that the user cannot register when leaving the telephone field blank")
    public void userCannotRegisterWhenTelephoneFieldLeftBlank() {
        String email = faker.internet().emailAddress();
        homePage.openRegisterPage();
        registerPage.registerANewUser(firstName, lastName, email, "", password, confirmPassword);
        registerPage.validateErrorMessageWhenTelephoneFieldLeftBlank();
    }

    @Test
    @Story("User cannot register with missing password")
    @Description("Validate that the user cannot register when leaving the password field blank")
    public void userCannotRegisterWhenPasswordFieldLeftBlank() {
        String email = faker.internet().emailAddress();
        homePage.openRegisterPage();
        registerPage.registerANewUser(firstName, lastName, email, telephone, "", confirmPassword);
        registerPage.validateErrorMessageWhenPasswordFieldLeftBlank();
    }

    @Test
    @Story("User cannot register with missing confirm password")
    @Description("Validate that the user cannot register when leaving the confirm password field blank")
    public void userCannotRegisterWhenConfirmPasswordFieldLeftBlank() {
        String email = faker.internet().emailAddress();
        homePage.openRegisterPage();
        registerPage.registerANewUser(firstName, lastName, email, telephone, password, "");
        registerPage.validateErrorMessageWhenConfirmPasswordFieldLeftBlank();
    }

    @Test
    @Story("User cannot register with already registered email")
    @Description("Validate that the user cannot register when using an already registered email")
    public void userCannotRegisterWithAlreadyRegisteredEmail() {
        homePage.openRegisterPage();
        registerPage.registerANewUser(firstName, lastName, "test@gmail.com", telephone, password, confirmPassword);
        registerPage.validateErrorMessageWhenEmailAlreadyRegistered();
    }

    @Test
    @Story("User cannot register without agreeing to privacy policy")
    @Description("Validate that the user cannot register when not agreeing to the privacy policy")
    public void userCannotRegisterWhenDidNotAgreeToPrivacyPolicy() {
        String email = faker.internet().emailAddress();
        homePage.openRegisterPage();
        registerPage.registerANewUserWithoutAgreePrivacyPolicy(firstName, lastName, email, telephone, password, confirmPassword);
        registerPage.validateErrorMessageWhenPrivacyPolicyNotAgreed();
    }


}
