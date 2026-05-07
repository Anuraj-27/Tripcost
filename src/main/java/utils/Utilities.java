package utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Utilities {

    WebDriver driver;
    ExtentTest test;

    public Utilities(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    public String captureScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") +
                "/target/screenshots/" + testName + "_" +
                System.currentTimeMillis() + ".png";
        File target = new File(destination);
        target.getParentFile().mkdirs();
        try {
            FileUtils.copyFile(source, target);
            test.info("Screenshot saved at: " + destination);
        } catch (IOException e) {
            test.fail("Failed to capture screenshot: " + e.getMessage());
        }
        return destination;
    }
}