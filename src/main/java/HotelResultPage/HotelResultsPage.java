package HotelResultPage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HotelResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Locators — IDs taken directly from hotels.html
    private By checkinInput   = By.id("checkin");
    private By checkoutInput  = By.id("checkout");
    private By searchBtn      = By.id("searchBtn");
    private By resultsSection = By.id("resultsSection");
    private By hotelNameCards  = By.cssSelector("[data-testid='hotel-name']");
    private By hotelPriceCards = By.cssSelector("[data-testid='price-per-night']");

    // Guest dropdown locators
    private By guestsBtn       = By.id("guestsBtn");
    private By guestsDropdown  = By.id("guestsDropdown");
    private By adultsCount     = By.id("adultsCount");
    private By adultsPlusBtn   = By.id("adultsPlusBtn");
    private By guestsUpdateBtn = By.id("guestsUpdateBtn");
    private By guestsSummary   = By.id("guestsSummary");

    public HotelResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js     = (JavascriptExecutor) driver;
    }

    public void setDates(String checkIn, String checkOut) {
        WebElement checkin  = wait.until(ExpectedConditions.presenceOfElementLocated(checkinInput));
        WebElement checkout = driver.findElement(checkoutInput);

        js.executeScript(
                "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                checkin, checkIn);
        js.executeScript(
                "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                checkout, checkOut);

        System.out.println("Check-in : " + checkin.getAttribute("value"));
        System.out.println("Check-out: " + checkout.getAttribute("value"));
    }

    public void setAdultsAndUpdate(int targetAdults) throws InterruptedException {
        // Open the guests dropdown
        driver.findElement(guestsBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestsDropdown));
        Thread.sleep(500);

        // Read current adult count (default is 2)
        int current = Integer.parseInt(
                driver.findElement(adultsCount).getText().trim());
        System.out.println("Current adults: " + current);

        // Click + until we reach targetAdults
        WebElement plusBtn = driver.findElement(adultsPlusBtn);
        for (int i = current; i < targetAdults; i++) {
            js.executeScript("arguments[0].click();", plusBtn);
            Thread.sleep(300);
        }

        System.out.println("Adults set to: "
                + driver.findElement(adultsCount).getText());

        // Click Update button
        driver.findElement(guestsUpdateBtn).click();
        Thread.sleep(500);

        System.out.println("Guest summary: "
                + driver.findElement(guestsSummary).getText());
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
        // resultsSection is hidden until a valid search is submitted
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsSection));
        wait.until(ExpectedConditions.presenceOfElementLocated(hotelNameCards));
    }

    public void printTopHotels(int count) throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> names  = driver.findElements(hotelNameCards);
        List<WebElement> prices = driver.findElements(hotelPriceCards);

        System.out.println("\n--- Top " + count + " Hotels ---");
        int limit = Math.min(count, names.size());
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + names.get(i).getText()
                    + " | " + prices.get(i).getText() + "/night");
        }
        System.out.println("Total hotels found: " + names.size());
    }
}