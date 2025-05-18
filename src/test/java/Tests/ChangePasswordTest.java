package Tests;

import Base.TestBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static Utils.Utils.readValue;

@Epic("User Management")
@Feature("Change Password")
public class ChangePasswordTest extends TestBase {

    String validpassword = readValue("validPassword");

    @Test
    @Story("User can change password successfully")
    @Description("Validate that the user can change their password to a new valid password")
    @Severity(SeverityLevel.CRITICAL)
    public void userCanChangePasswordSuccessfully() {
        // Login
        homePage.openLoginPage();
        loginPage.login(email, validpassword);
        loginPage.assertSuccessfulLoginMessage();

        // Change password
        changePasswordPage.changePasswordToNewPassword("Tester123$");
        changePasswordPage.assertSuccessMessageWhenChangeToNewPassword();

        // Logout
        homePage.loginOut();
    }

    @Test
    @Story("User cannot change password to the same password")
    @Description("Validate that the user cannot change their password to the same current password")
    @Severity(SeverityLevel.NORMAL)
    public void userCannotChangePasswordToTheSamePassword() {
        // Login
        homePage.openLoginPage();
        loginPage.login(email, "Tester123$");

        // Attempt to change to same password
        changePasswordPage.changePasswordToSamePassword("Tester123$");
        changePasswordPage.assertErrorMessageWhenChangeToSamePassword();

        // Logout
        homePage.loginOut();
    }
}
