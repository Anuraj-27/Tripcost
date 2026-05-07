package CruisePage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CruisePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators — IDs taken directly from cruises.html
    // cruise line cards are rendered as: cruiseLine-{lineId}
    // ship cards are rendered as:        ship-{shipId}
    private By specPassengers = By.id("specPassengers");
    private By specCrew       = By.id("specCrew");
    private By specLaunched   = By.id("specLaunched");
    private By languageTags   = By.cssSelector("[data-testid='language-tag']");

    public CruisePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void selectCruiseLine(String lineId) {
        // e.g. lineId = "royal"  → clicks element with id="cruiseLine-royal"
        WebElement lineCard = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("cruiseLine-" + lineId)));
        lineCard.click();
        System.out.println("Selected cruise line: " + lineId);
    }

    public void selectShip(String shipId) {
        // e.g. shipId = "icon"  → clicks element with id="ship-icon"
        WebElement shipCard = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ship-" + shipId)));
        shipCard.click();
        System.out.println("Selected ship: " + shipId);
    }

    public void printShipDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(specPassengers));
        Thread.sleep(1000); // buffer for JS render

        System.out.println("\n--- Ship Details ---");
        System.out.println("Passengers : " + driver.findElement(specPassengers).getText());
        System.out.println("Crew       : " + driver.findElement(specCrew).getText());
        System.out.println("Launched   : " + driver.findElement(specLaunched).getText());
    }

    public void printLanguages() {
        List<WebElement> tags = driver.findElements(languageTags);
        List<String> languages = new ArrayList<>();
        for (WebElement tag : tags) languages.add(tag.getText());
        System.out.println("Languages  : " + languages);
    }
}
