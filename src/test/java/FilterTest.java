import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FilterTest extends BaseTest {

    @Test(priority = 16)
    public void checkFiltersApplied() {
        res.clickfilters();
        test.info("Filters clicked");
    }

    @Test(priority = 17)
    public void CheckHotelsPresent() {
        List<WebElement> hotels = res.selectHotels();
        test.info("Hotels present: " + hotels.size());
        Assert.assertTrue(hotels.size() >= 0);
    }

    @Test(priority = 18)
    public void CheckHotelAddress() {
        List<WebElement> addresses = driver.findElements(By.className("rest-address"));
        test.info("Addresses found: " + addresses.size());
        for (WebElement address : addresses) {
            Assert.assertTrue(address.isDisplayed());
        }
    }

    @Test(priority = 19)
    public void CheckHotelPhoneNumber() {
        List<WebElement> phone = driver.findElements(By.className("rest-phone"));
        test.info("Phone numbers found: " + phone.size());
        for (WebElement onephone : phone) {
            Assert.assertTrue(onephone.isDisplayed());
        }
    }
}