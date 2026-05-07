import org.testng.Assert;
import org.testng.annotations.Test;

public class RestaurantTest extends BaseTest {

    @Test(priority = 15)
    public void validateRestaurant() throws Exception {
        res.BookRestaurant();
        test.info("Restaurant booking navigated to: " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://trip2-pi.vercel.app/restaurants.html");
    }
}