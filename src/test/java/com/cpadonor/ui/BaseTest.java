package com.cpadonor.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.cpadonor.web.LoginSteps;
import org.example.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    protected final String BASE_URL = "https://cpadonor.com/en";
    protected final String USER_EMAIL = "test_user@gmail.com";
    protected final String USER_PASS = "jdskladJASKLDJA";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login() {
        driver.get(BASE_URL + "/login");
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginAction(USER_EMAIL, USER_PASS);
        try {
            wait.until(ExpectedConditions.urlContains("dashboard"));
        } catch (Exception e) {
            Assert.fail("error login. current url: " + driver.getCurrentUrl());
        }
        Assert.assertFalse(driver.getCurrentUrl().contains("/login"), "error login");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}