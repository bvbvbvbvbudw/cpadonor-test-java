package com.cpadonor.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SourcesPage {
    WebDriver driver;

    public SourcesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[contains(@onclick, 'openAddSourceModal')]")
    private WebElement addSourceBtn;
    @FindBy(css = "#addSourceForm input[name='name']")
    private WebElement nameInput;
    @FindBy(css = "#addSourceForm input[name='url']")
    private WebElement urlInput;
    @FindBy(css = "#addSourceForm textarea[name='description']")
    private WebElement descInput;
    @FindBy(css = "#addSourceForm button[type='submit']")
    private WebElement submitBtn;
    public void clickAddButton() {
        addSourceBtn.click();
    }
    public void fillSourceForm(String name, String url, String desc) throws InterruptedException {
        Thread.sleep(500);

        nameInput.clear();
        nameInput.sendKeys(name);

        urlInput.clear();
        urlInput.sendKeys(url);

        if (desc != null) {
            descInput.clear();
            descInput.sendKeys(desc);
        }
    }

    public void submitForm() {
        submitBtn.click();
    }

    public boolean isSourcePresent(String sourceName) {
        try {
            WebElement card = driver.findElement(By.xpath("//h3[contains(@class, 'card-title') and contains(text(), '" + sourceName + "')]"));
            return card.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}