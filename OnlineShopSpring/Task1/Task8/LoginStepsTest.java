package ru.geekbrains;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepsTest {
    private WebDriver webDriver;

    @Given("^I open web browser$")
    public void iOpenChromeBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\spring-part-two\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() {
        webDriver.get("http://localhost:8080/");
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        webDriver.findElement(By.id("user_name")).sendKeys(username);
        Thread.sleep(1000);
        webDriver.findElement(By.id("user_password")).sendKeys(password);
        Thread.sleep(1000);
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() {
        webDriver.findElement(By.id("btnSubmit")).click();
    }

    @When("^I click on Users$")
    public void iClickOnUsers() throws InterruptedException {
        webDriver.findElement(By.id("user_link")).click();
        Thread.sleep(1000);
    }

    @When("^I click on Brands$")
    public void iClickOnBrands() throws InterruptedException {
        webDriver.findElement(By.id("brand_link")).click();
        Thread.sleep(1000);
    }

    @When("^I Open dropdown menu$")
    public void openDropDownMenu() throws InterruptedException {
        webDriver.findElement(By.id("drop_menu")).click();
        Thread.sleep(1000);
    }

    @When("^I click logout button$")
    public void clickLogoutButton() throws InterruptedException {
        webDriver.findElement(By.id("logout_item")).click();
        Thread.sleep(1000);
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}