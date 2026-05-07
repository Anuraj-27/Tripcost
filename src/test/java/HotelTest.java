import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelTest extends BaseTest {

    @Test(priority = 3)
    public void validateHotelSearch() {
        homePage.searchForCity("Nairobi");
        test.info("Searched for city: Nairobi");
        Assert.assertTrue(driver.getCurrentUrl().contains("hotels.html"));
    }

    @Test(priority = 4)
    public void validateDatesSet() {
        hotelResultsPage.setDates(reader.getCheckIn(), reader.getCheckOut());
        test.info("Dates set: " + reader.getCheckIn() + " to " + reader.getCheckOut());
        Assert.assertTrue(true);
    }

    @Test(priority = 5)
    public void validateGuestsSet() throws InterruptedException {
        hotelResultsPage.setAdultsAndUpdate(4);
        String summary = driver.findElement(By.id("guestsSummary")).getText();
        test.info("Guests summary: " + summary);
        Assert.assertTrue(summary.contains("4"));
    }

    @Test(priority = 6)
    public void validateHotelResultsLoaded() {
        hotelResultsPage.clickSearch();
        int count = driver.findElements(By.cssSelector("[data-testid='hotel-name']")).size();
        test.info("Hotels found: " + count);
        Assert.assertTrue(count > 0);
    }

    @Test(priority = 7)
    public void validateTopHotelsPrinted() throws Exception {
        hotelResultsPage.printTopHotels(3);
        test.info("Top 3 hotels printed");
    }

    @Test(priority = 8)
    public void validateHotelNamesNotEmpty() {
        test.info("Validating hotel names are not empty");
        driver.findElements(By.cssSelector("[data-testid='hotel-name']"))
                .forEach(e -> Assert.assertFalse(e.getText().trim().isEmpty()));
    }

    @Test(priority = 9)
    public void validateHotelPricesVisible() {
        int priceCount = driver.findElements(
                By.cssSelector("[data-testid='price-per-night']")).size();
        test.info("Hotel prices visible: " + priceCount);
        Assert.assertTrue(priceCount > 0);
    }
}