package restaurantsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Restaurant {

    private  WebDriver driver;

    public  Restaurant(WebDriver driver)
    {
        this.driver=driver;
    }


        public void BookRestaurant () {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement restaurants = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.linkText("Restaurants"))
            );
              restaurants.click();

            System.out.println(" Clicked Restaurants");
//            wait.until(driver -> ((JavascriptExecutor) driver)
//                    .executeScript("return document.readyState").equals("complete"));

        }
        public void clickfilters()
      {
          WebElement breakfast=driver.findElement(By.id("mealBreakfast"));
          breakfast.click();

          System.out.println("Breakfast selected");
          WebElement cuisine=driver.findElement(By.id("cuisineAfrican"));
          cuisine.click();
          WebElement dish =driver.findElement(By.id("dishFish"));
          dish.click();
      }

      public List<WebElement> selectHotels()
      {
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
          wait.until(ExpectedConditions.presenceOfElementLocated(By.className("rest-name")));

          List<WebElement> titles = driver.findElements(By.className("rest-name"));
          List<WebElement> rating=  driver.findElements(By.className("rating-text"));
          List<WebElement> reviews= driver.findElements(By.className("review-count"));
          List<WebElement> address= driver.findElements(By.className("rest-address"));
          List<WebElement> phone=   driver.findElements(By.className("rest-phone"));

          System.out.println("--Restaurant Details:-- ");
          for (int i=0; i< titles.size(); i++) {
              System.out.println("Restaurant: " + titles.get(i).getText());
              System.out.println("Ratings: "+ rating.get(i).getText());
              System.out.println("Reviews: "+ reviews.get(i).getText());
              System.out.println("Address: "+ address.get(i).getText());
              System.out.println("Phone: "+ phone.get(i).getText());
              System.out.println("-------------------------------------");
              System.out.println();
          }
          return titles;
      }

}
