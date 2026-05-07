package HomePage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Locators — IDs taken directly from index.html
    private By searchInput = By.id("globalSearch");
    private By searchBtn   = By.cssSelector(".btn-search");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js     = (JavascriptExecutor) driver;
    }

    public void waitForPageLoad() {
        wait.until(d -> "complete".equals(js.executeScript("return document.readyState")));
    }

    public void searchForCity(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys(city);
        driver.findElement(searchBtn).click();
        System.out.println("Searched for: " + city);
    }
}
