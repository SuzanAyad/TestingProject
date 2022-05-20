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
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class SliderWrapper {
    WebDriver webDriver = null;
    HomePage homePage;
    Actions actions;
    SoftAssert softAssert;

    @Before("@Slider")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("user navigates to home page")
    public void navigationToHome(){
        webDriver.navigate().to("https://demo.nopcommerce.com/");
    }

    @And("hovers with mouse to slider wrapper")
    public void hoverOnSlider(){
        actions = new Actions(webDriver);
        actions.moveToElement(homePage.slider());
    }

    @And("clicks on the slider")
    public void clickOnSlider(){
        actions.click().build().perform();
    }

    @Then("user could be navigated to item page")
    public void validSliderClick(){
        String expectUrl = "https://demo.nopcommerce.com/";
        String actualUrl = webDriver.getCurrentUrl();
        softAssert = new SoftAssert();
        softAssert.assertEquals(expectUrl, actualUrl);
        softAssert.assertAll();
    }

    @After("@Slider")
    public void exit(){
        webDriver.quit();
    }
}
