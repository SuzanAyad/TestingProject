package Steps;

import Pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class FollowUs {
    WebDriver webDriver = null;
    HomePage homePage;
    SoftAssert softAssert;

    @Before("@FollowUs")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
    }
    @When("user navigates to homepage to try follow us option")
    public void navigationToHome(){
        webDriver.navigate().to("https://demo.nopcommerce.com/");
    }
    @And("user clicks on facebook logo under follow us label")
    public void clickOnFacebookLogo() throws InterruptedException {
        homePage.facebookLogo().click();
        Thread.sleep(3000);
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        Thread.sleep(3000);
    }

    @Then("a new tab directing to facebook page should be opened")
    public void validFacebookRedirection(){
        String expectUrl = "https://www.facebook.com/nopCommerce";
        String actualUrl = webDriver.getCurrentUrl();
        softAssert = new SoftAssert();
        softAssert.assertEquals(expectUrl, actualUrl);
        softAssert.assertAll();
    }

    @After("@FollowUs")
    public void exit(){
        webDriver.quit();
    }
}
