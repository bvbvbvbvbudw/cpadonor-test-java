package com.cpadonor.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FlowWizardPage {
    WebDriver driver;
    WebDriverWait wait;

    public FlowWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "flowName")
    private WebElement nameInput;

    @FindBy(xpath = "//select[@name='user_source_id']/following-sibling::div[contains(@class, 'choices')]")
    private WebElement sourceSelectWrapper;

    @FindBy(css = "button[type='submit']")
    private WebElement nextBtn;

    @FindBy(id = "landing-list")
    private WebElement landingListContainer;

    @FindBy(xpath = "//select[@name='tracking_domain_id']/following-sibling::div[contains(@class, 'choices')]")
    private WebElement domainSelectWrapper;

    @FindBy(id = "is_postback_enabled")
    private WebElement postbackCheckbox;

    public void fillStep1(String flowName, String sourceName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        nameInput.sendKeys(flowName);
        selectOptionInChoices(sourceSelectWrapper, sourceName);
        nextBtn.click();
    }

    public void selectFirstLanding() {
        wait.until(ExpectedConditions.visibilityOf(landingListContainer));
        List<WebElement> cards = landingListContainer.findElements(By.className("selection-card"));
        if (!cards.isEmpty()) {
            cards.get(0).click();
        } else {
            throw new RuntimeException("error landing-list");
        }
        nextBtn.click();
    }

    public void fillStep3() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(domainSelectWrapper));
        selectOptionInChoices(domainSelectWrapper, "vertanet.fun");
        if (postbackCheckbox.isSelected()) {
            postbackCheckbox.click();
            System.out.println("LOG: Postback checkbox was checked, clicked to disable.");
        }
        nextBtn.click();
    }

    private void selectOptionInChoices(WebElement wrapper, String textToSelect) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(wrapper));
        wrapper.click();
        Thread.sleep(500);

        By optionsLocator = By.cssSelector(".choices__list--dropdown.is-active .choices__item--choice");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsLocator));
        List<WebElement> options = driver.findElements(optionsLocator);

        if (textToSelect == null) {
            if (!options.isEmpty()) options.get(0).click();
        } else {
            boolean found = false;
            for (WebElement opt : options) {
                if (opt.getText().trim().contains(textToSelect)) {
                    opt.click();
                    found = true;
                    break;
                }
            }
            if (!found && !options.isEmpty()) options.get(0).click();
        }
    }
}