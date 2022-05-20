package Steps;

import Pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class CategorySelection {
    WebDriver webDriver = null;
    HomePage homePage;
    Actions actions;
    SoftAssert softAssert;

    @Before("@SelectCategory")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("user navigates to home page to select category")
    public void navigationToHome(){
        webDriver.navigate().to("https://demo.nopcommerce.com/");
    }

    @And("hovers to Apparel category")
    public void hoverToCategory(){
        actions = new Actions(webDriver);
        actions.moveToElement(homePage.apparelCategory());
    }

    @And("clicks on Shoes from Apparel menu")
    public void clickOnSubcategory(){
        actions.moveToElement(homePage.shoesSubcategory());
        actions.click().build().perform();
    }

    @Then("user should be directed to Shoes page")
    public void validCategory(){
        String expectUrl = "https://demo.nopcommerce.com/shoes";
        String actualUrl = webDriver.getCurrentUrl();
        softAssert = new SoftAssert();
        softAssert.assertEquals(expectUrl, actualUrl);
        softAssert.assertAll();
    }

    @After("@SelectCategory")
    public void exit(){
        webDriver.quit();
    }

}
