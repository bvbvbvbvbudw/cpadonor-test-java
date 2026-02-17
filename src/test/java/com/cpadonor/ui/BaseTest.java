package com.cpadonor.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.cpadonor.config.TestConfig;
import com.cpadonor.web.LoginSteps;
import org.example.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    protected final String BASE_URL = TestConfig.baseUrl();
    protected final String USER_EMAIL = TestConfig.userEmail();
    protected final String USER_PASS = TestConfig.userPass();

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (TestConfig.isCi()) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        driver = new ChromeDriver(options);
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