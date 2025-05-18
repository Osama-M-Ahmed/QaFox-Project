package Base;

import Pages.*;
import Utils.ScreenShot;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

import static Utils.Utils.readValue;

@Listeners({AllureTestNg.class})
public class TestBase {

    protected WebDriver driver;
    protected HomePage homePage;
    protected PageBase pageBase;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected ChangePasswordPage changePasswordPage;
    protected ProductPage productPage;

    public static String email;

    @Parameters("browser")
    @BeforeClass
    public void precondition(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        pageBase = new PageBase(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        changePasswordPage = new ChangePasswordPage(driver);
        productPage = new ProductPage(driver);


    }

    @Parameters("browser")
    @AfterMethod
    public void takeScreenShot(ITestResult test_result, @Optional("None") String browser_name) {
        if (ITestResult.FAILURE == test_result.getStatus())
            ScreenShot.take_screenshot(this.driver, test_result.getName(), browser_name);
        this.driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}