package Steps;

import Pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class Wishlist {
    WebDriver webDriver = null;
    HomePage homePage;
    SoftAssert softAssert;

    @Before("@Wishlist")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("user navigates to home page to add product to wishlist")
    public void navigationToHome(){
        webDriver.navigate().to("https://demo.nopcommerce.com/");
    }

    @And("clicks on heart symbol under HTC ONE M8 mobile phone")
    public void clickOnWishlistSymbol() throws InterruptedException {
        homePage.wishlistPhone().click();
        Thread.sleep(1000);
    }

    @Then("Success message should be displayed")
    public void validWishlistMessage(){
        String successMessageText = "The product has been added to your wishlist";
        String actualMessageText = homePage.successNotification().getText();
        softAssert = new SoftAssert();
        softAssert.assertTrue(actualMessageText.contains(successMessageText));
        softAssert.assertTrue(homePage.successNotification().getCssValue("background-color").contains("rgba(75, 176, 122, 1)"));
        softAssert.assertAll();
    }

    @And("Product should be displayed in Wishlist page")
    public void validWishlistView(){
        homePage.link().click();
        softAssert.assertTrue(webDriver.findElement(By.cssSelector("img[alt=\"Picture of HTC One M8 Android L 5.0 Lollipop\"]")).isDisplayed());
        softAssert.assertAll();
    }

    @After("@Wishlist")
    public void exit(){
        webDriver.quit();
    }
}

