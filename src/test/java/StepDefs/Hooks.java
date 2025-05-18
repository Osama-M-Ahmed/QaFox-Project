package StepDefs;

import Pages.HomePage;
import Pages.PageBase;
import Pages.RegisterPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Hooks {

    public static WebDriver driver;


    @Before
    public void preconditions() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @After
    public void tearDown() {
        driver.quit();
    }


}
