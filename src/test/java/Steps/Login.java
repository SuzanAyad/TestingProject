package Steps;

import Pages.HomePage;
import Pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;


public class Login {
    WebDriver webDriver;
    LoginPage loginPage;
    HomePage homePage;
    SoftAssert softAssert;

    public void loginInput(String email, String password){
        loginPage.email().clear();
        loginPage.password().clear();
        loginPage.email().sendKeys(email);
        loginPage.password().sendKeys(password);
    }


    @Before("@Login")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("user navigates to homepage url")
    public void navigation() {
        webDriver.navigate().to("https://demo.nopcommerce.com");
    }

    @And("user clicks on login tab")
    public void clickOnLogin() throws InterruptedException {
        homePage.loginTab().click();
        Thread.sleep(5000);
    }

    @And("^user enters \"(.*)\" as an email and \"(.*)\" as a password$")
    public void validReg(String email, String password) {
        loginInput(email, password);
    }

    @And("clicks on Login")
    public void submitting() {
        loginPage.submit().click();
    }

    @Then("user could successfully logged in")
    public void result() {
        String expectedUrl = "https://demo.nopcommerce.com/";
        String actualUrl = webDriver.getCurrentUrl();
        String expectedMyAccount = "My account";
        String actualMyAccount = homePage.firstItem().getText();
        softAssert = new SoftAssert();
        softAssert.assertEquals(expectedUrl, actualUrl);
        softAssert.assertEquals(expectedMyAccount,actualMyAccount);
        softAssert.assertAll();
    }

    @After("@Login")
    public void exit(){
        webDriver.quit();
    }
}
