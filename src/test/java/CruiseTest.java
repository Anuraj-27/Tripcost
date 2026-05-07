import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CruiseTest extends BaseTest {

    @Test(priority = 10)
    public void validateCruisesNavigation() {
        driver.findElement(By.id("navCruises")).click();
        test.info("Navigated to cruises page");
        Assert.assertTrue(driver.getCurrentUrl().contains("cruises.html"));
    }

    @Test(priority = 11)
    public void validateCruiseLineSelection() {
        cruisePage.selectCruiseLine(reader.getCruiseLine());
        test.info("Cruise line selected: " + reader.getCruiseLine());
        Assert.assertNotNull(wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("ship-" + reader.getShipId()))));
    }

    @Test(priority = 12)
    public void validateShipSelection() {
        cruisePage.selectShip(reader.getShipId());
        WebElement passengers = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("specPassengers")));
        test.info("Ship selected, passengers element visible");
        Assert.assertTrue(passengers.isDisplayed());
    }

    @Test(priority = 13)
    public void validateShipDetailsPrinted() throws Exception {
        cruisePage.printShipDetails();
        test.info("Ship details printed");
    }

    @Test(priority = 14)
    public void validateLanguagesList() {
        cruisePage.printLanguages();
        int langCount = driver.findElements(
                By.cssSelector("[data-testid='language-tag']")).size();
        test.info("Languages found: " + langCount);
        Assert.assertTrue(langCount > 0);
    }
}