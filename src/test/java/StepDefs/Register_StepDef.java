package StepDefs;

import Pages.HomePage;
import Pages.PageBase;
import Pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static StepDefs.Hooks.driver;

public class Register_StepDef {


    HomePage homePage = new HomePage(driver);
    RegisterPage registerPage = new RegisterPage(driver);
    PageBase pageBase = new PageBase(driver);

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        // Code to navigate to the home page
        pageBase.navigateToWebsite();
        pageBase.waitForTextToAppear("Qafox.com", 5);
    }


    @When("the user clicks on Account and selects Register")
    public void theUserClicksOnAndSelects() {
        homePage.openRegisterPage();
    }


    @And("user fills in the registration form with email {string}, {string}, {string},{string}, {string}, {string}")
    public void userFillsInTheRegistrationFormWithEmail(String firstname, String lastname, String email, String telephone, String password, String confirmPassword) {
        registerPage.registerANewUser(firstname, lastname, email, telephone, password, confirmPassword);
    }


    @Then("the registration should be completed successfully")
    public void theRegistrationShouldBeCompletedSuccessfully() {
        String expectedText = "Your Account Has Been Created!";
        String actualText = registerPage.getSuccessMessageText();
        pageBase.waitForTextToAppear(expectedText, 10);
        Assert.assertTrue(actualText.contains(expectedText), "Failed, User can register without lastname!");
    }


    @And("the user should see the confirmation message")
    public void theUserShouldSeeTheConfirmationMessage() {
        Assert.assertTrue(registerPage.getSuccessMessageText().contains("Your Account Has Been Created!"),
                "Confirmation message not displayed or incorrect.");
        homePage.loginOut();
    }


}

