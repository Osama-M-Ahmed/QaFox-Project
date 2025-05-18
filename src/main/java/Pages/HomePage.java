package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends PageBase {

    //Constractor
    private Actions actions;

    public HomePage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver); // Ensure driver is not null
    }

    //Locators

    By myAccount = By.xpath("//*[@title=\"My Account\"]");
    By register = By.xpath("//*[text()='Register']");
    By login = By.xpath("//*[text()='Login']");
    By currency = By.xpath("//*[@class=\"btn btn-link dropdown-toggle\"]");
    By pound = By.xpath("//*[@class=\"currency-select btn btn-link btn-block\"]");
    By phones = By.xpath("//*[text()='Phones & PDAs']");
    By loginOut = By.xpath("/html/body/div[2]/div/aside/div/a[13]");

    //Actions

    public void openRegisterPage() {
        waitUntilVisible(myAccount);
        click(myAccount);
        click(register);
    }

    public void openLoginPage() {
        click(myAccount);
        click(login);
    }

    public void choosePoundPrices() {
        waitUntilVisible(currency);
        click(currency);
        click(pound);
    }

    public void selectPhonesCategory() {
        waitUntilVisible(phones);
        click(phones);
    }

    public void loginOut() {
        click(loginOut);
    }


}
