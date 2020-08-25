package ru.geekbrains;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void tryLogin() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\spring-part-two\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");

        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("user_password")).sendKeys("admin");
        driver.findElement(By.id("btnSubmit")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("user_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("role_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("category_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("brand_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("product_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("drop_menu")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_item")).click();
        Thread.sleep(1000);
        driver.quit();
    }
}
