package com.cpadonor.web.bo;

import com.cpadonor.web.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginBO {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    public LoginBO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.loginPage = new LoginPage(driver);
    }

    public void login(String email, String password) {
        if (!driver.getCurrentUrl().contains("login")) {
            driver.get("https://cpadonor.com/en/login");
        }

        loginPage.inputEmail(email);
        loginPage.inputPass(password);
        loginPage.clickLogin();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
    }
}