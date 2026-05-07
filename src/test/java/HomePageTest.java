import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    public void validateHomePageTitle() {
        String title = driver.getTitle();
        test.info("Page title: " + title);
        Assert.assertFalse(title.isEmpty(), "Page title is empty");
    }

    @Test(priority = 2)
    public void validateHomePageLoaded() {
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("globalSearch")));
        test.info("Search box is visible");
        Assert.assertTrue(searchBox.isDisplayed(), "Search box not visible");
    }
}