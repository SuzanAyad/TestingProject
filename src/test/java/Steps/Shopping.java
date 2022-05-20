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

public class Shopping {
    WebDriver webDriver = null;
    HomePage homePage;
    SoftAssert softAssert;

    @Before("@ShoppingCart")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("user navigates to home page to add product to shopping cart")
    public void navigationToHome(){
        webDriver.navigate().to("https://demo.nopcommerce.com/");
    }

    @And("clicks on ADD TO CART under HTC ONE M8 mobile phone")
    public void clickOnAddToCart() throws InterruptedException {
        homePage.shoppingPhone().click();
        Thread.sleep(1000);
    }
    @Then("Success message should be displayed for cart")
    public void validShoppingMessage(){
        String successMessageText = "The product has been added to your shopping cart";
        String actualMessageText = homePage.successNotification().getText();
        softAssert = new SoftAssert();
        softAssert.assertTrue(actualMessageText.contains(successMessageText));
        softAssert.assertTrue(homePage.successNotification().getCssValue("background-color").contains("rgba(75, 176, 122, 1)"));
        softAssert.assertAll();
    }

    @And("Product should be displayed in Shopping cart page")
    public void validShoppingView(){
        homePage.link().click();
        softAssert.assertTrue(webDriver.findElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[1]/span")).getText().contains("M8_HTC_5L"));
        softAssert.assertAll();
    }

    @After("@ShoppingCart")
    public void exit(){
        webDriver.quit();
    }
}
