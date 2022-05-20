package Steps;

import Pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class Search {
    WebDriver webDriver = null;
    HomePage homePage;
    SoftAssert softAssert;

    @Before("@Search")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("user navigates to home page to search")
    public void navigationToHomForSearching(){
        webDriver.navigate().to("https://demo.nopcommerce.com/");
    }

    @And("^user inputs \"(.*)\" as a name in search and hits inter$")
    public void inputName(String productName) throws InterruptedException {
        homePage.searchField().clear();
        homePage.searchField().sendKeys(productName);
        homePage.searchField().sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }

    @And("^user inputs \"(.*)\" as a SKU in search and hits inter$")
    public void inputSKU(String productName) throws InterruptedException {
        homePage.searchField().clear();
        homePage.searchField().sendKeys(productName);
        homePage.searchField().sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }

    @Then("user could see the item displayed")
    public void validSearch(){
        WebElement adidasImage = webDriver.findElement(By.cssSelector("img[alt=\"Picture of adidas Consortium Campus 80s Running Shoes\"]"));
        softAssert = new SoftAssert();
        softAssert.assertTrue(adidasImage.isDisplayed());
        softAssert.assertAll();
    }

    @After("@Search")
    public void exit(){
        webDriver.quit();
    }
}
