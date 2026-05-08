import CruisePage.CruisePage;
import HomePage.HomePage;
import HotelResultPage.HotelResultsPage;
import restaurantsPage.Restaurant;
import config.ObjectReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Utilities;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    // Per page repository
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static ObjectReader reader;
    protected static HomePage homePage;
    protected static HotelResultsPage hotelResultsPage;
    protected static CruisePage cruisePage;
    protected static Restaurant res;
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected Utilities utilities;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/target/Spark.html"
        );
        spark.config().setReportName("TripCost Test Report");
        spark.config().setDocumentTitle("Selenium Test Results");
        extent = new ExtentReports();
        extent.setSystemInfo("Tester", "QA Team");
        extent.setSystemInfo("Environment", "QA");
        extent.attachReporter(spark);
    }

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional("chrome") String browser) throws Exception {

        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();
            extent.setSystemInfo("Browser", "Chrome");

        } else if (browser.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();
            extent.setSystemInfo("Browser", "Edge");

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        reader = new ObjectReader();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        homePage = new HomePage(driver);
        hotelResultsPage = new HotelResultsPage(driver);
        cruisePage = new CruisePage(driver);
        res = new Restaurant(driver);

        driver.get(reader.getUrl());
        homePage.waitForPageLoad();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        test = extent.createTest(method.getName());
        utilities = new Utilities(driver, test);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = utilities.captureScreenshot(result.getName());
            test.fail("Test Failed: " + result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip(result.getThrowable());
        } else {
            test.pass("Test passed successfully");
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}