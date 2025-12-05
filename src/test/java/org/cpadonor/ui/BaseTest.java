package org.cpadonor.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.cpadonor.web.LoginSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    protected final String BASE_URL = "http://localhost:8000";
    protected final String USER_EMAIL = "user@test.com";
    protected final String USER_PASS = "secretsecret";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void login() throws InterruptedException {
        driver.get(BASE_URL + "/login");
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginAction(USER_EMAIL, USER_PASS);
        // зробити перевірку що юзер війшов

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}