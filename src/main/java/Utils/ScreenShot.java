package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShot {

    public static void take_screenshot(WebDriver driver, String tc_name, String browser_name) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        File screenshot_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filename = "screenshots/" + tc_name + "_" + now.format(formatter) + "_" + browser_name + ".png";
        try {
            FileUtils.copyFile(screenshot_file, new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
