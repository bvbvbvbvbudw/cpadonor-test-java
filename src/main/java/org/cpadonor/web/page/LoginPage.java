package org.cpadonor.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void inputEmail(String text) {
        emailField.sendKeys(text);
    }

    public void inputPass(String text) {
        passField.sendKeys(text);
    }

    public void clickLogin() {
        loginBtn.click();
    }
}