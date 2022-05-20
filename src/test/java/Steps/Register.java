package Steps;

import Pages.HomePage;
import Pages.RegistrationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class Register {
    WebDriver webDriver;
    RegistrationPage registrationPage;
    SoftAssert softAssert;
    HomePage homePage;
    Select selectDay;
    Select selectMonth;
    Select selectYear;

    public void registerInput(String firstName, String lastName, String email){
        registrationPage.firstName().clear();
        registrationPage.lastName().clear();
        registrationPage.email().clear();
        registrationPage.firstName().sendKeys(firstName);
        registrationPage.lastName().sendKeys(lastName);
        registrationPage.email().sendKeys(email);
    }
    public void passwordInput(String password, String confirmPassword){
        registrationPage.password().clear();
        registrationPage.confirmPassword().clear();
        registrationPage.password().sendKeys(password);
        registrationPage.confirmPassword().sendKeys(confirmPassword);
    }

    @Before("@Register")
    public void driver(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("user navigates to website url")
    public void navigation() throws InterruptedException {
        webDriver.navigate().to("https://demo.nopcommerce.com");
        Thread.sleep(3000);
    }

    @And("Click on Register Tab")
    public void clickOnRegister(){
        homePage.registerTab().click();
    }

    @And("select gender option")
    public void genderSelection(){
        registrationPage.genderSelectionMale().click();
    }

    @And("^user enters \"(.*)\" as a first name, \"(.*)\" as a last name, \"(.*)\" as an email$")
    public void validReg(String firstName, String lastName, String email)  {
        registerInput(firstName, lastName, email);
    }

    @And("Select Day, Month and Year")
    public void dateSelection(){
        selectDay = new Select(webDriver.findElement(By.name("DateOfBirthDay")));
        selectMonth = new Select(webDriver.findElement(By.name("DateOfBirthMonth")));
        selectYear = new Select(webDriver.findElement(By.name("DateOfBirthYear")));
        selectDay.selectByVisibleText("2");
        selectMonth.selectByVisibleText("October");
        selectYear.selectByVisibleText("1985");
    }

    @And("^fill \"(.*)\" as company name$")
    public void companyInput(String company){
        registrationPage.company().clear();
        registrationPage.company().sendKeys(company);
    }

    @And("^fill \"(.*)\" as a password and \"(.*)\" as a confirm password$")
    public void validPassword(String password, String confirmPassword){
        passwordInput(password, confirmPassword);
    }

    @And("clicks on Register")
    public void submitting() {
        registrationPage.submit().click();
    }

    @Then("user could successfully register")
    public void result() {
        String expectedMessage = "Your registration completed";
        String actualMessage = webDriver.findElement(By.className("result")).getText();
        softAssert = new SoftAssert();
        softAssert.assertTrue(webDriver.findElement(By.className("result"))
                .getCssValue("color")
                .contains("rgba(76, 177, 124, 1)"));
        softAssert.assertTrue(actualMessage.contains(expectedMessage));
        softAssert.assertAll();
    }

    @After("@Register")
    public void exit(){
        webDriver.quit();
    }
}

