package com.cpadonor.web;

import com.cpadonor.web.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void loginAction(String email, String password) {
        System.out.println("LOG: email: " + email);
        loginPage.inputEmail(email);
        loginPage.inputPass(password);
        loginPage.clickLogin();
    }
}