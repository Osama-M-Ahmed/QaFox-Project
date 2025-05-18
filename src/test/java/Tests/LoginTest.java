package Tests;

import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Utils.Utils.readValue;

@Epic("User Management")
@Feature("Login")
public class LoginTest extends TestBase {

    String validEmail = readValue("validEmail");
    String validPassword = readValue("validPassword");
    String invalidMail = readValue("invalidMail");
    String invalidPassword = readValue("invalidPassword");

    @Story("Valid user can login successfully with correct credentials")
    @Description("Validate that the user can login successfully using valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void userCanLoginSuccessfully() {
        homePage.openLoginPage();
        loginPage.login(email, validPassword);
        loginPage.assertSuccessfulLoginMessage();
        homePage.loginOut();
    }

    @Story("User cannot login with invalid email")
    @Description("Validate that login fails when using an invalid email address")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void userCannotLoginWithInvalidEmail() {
        homePage.openLoginPage();
        loginPage.login(invalidMail, validPassword);
        loginPage.assertLoginErrorMessage();
    }

    @Story("User cannot login with invalid password")
    @Description("Validate that login fails when using an incorrect password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void userCannotLoginWithInvalidPassword() {
        homePage.openLoginPage();
        loginPage.login(email, invalidPassword);
        loginPage.assertLoginErrorMessage();
    }

    @Story("User cannot login with empty email")
    @Description("Validate that login fails when the email field is left empty")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void userCannotLoginWithEmptyEmail() {
        homePage.openLoginPage();
        loginPage.login("", validPassword);
        loginPage.assertLoginErrorMessage();
    }

    @Story("User cannot login with empty password")
    @Description("Validate that login fails when the password field is left empty")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void userCannotLoginWithEmptyPassword() {
        homePage.openLoginPage();
        loginPage.login(email, "");
        loginPage.assertLoginErrorMessage();
    }

    @Story("User cannot login with both fields empty")
    @Description("Validate that login fails when both email and password fields are left empty")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void userCannotLoginWithEmptyFields() {
        homePage.openLoginPage();
        loginPage.login("", "");
        loginPage.assertLoginErrorMessage();
    }



}
