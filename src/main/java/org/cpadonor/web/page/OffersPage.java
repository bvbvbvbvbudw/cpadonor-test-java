package org.cpadonor.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OffersPage {
    WebDriver driver;

    public OffersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "search")
    private WebElement searchInput;

    @FindBy(css = ".search-btn-abs")
    private WebElement searchBtn;
    @FindBy(xpath = "//select[@name='category_id']/following-sibling::div[contains(@class, 'choices')]")
    private WebElement categoryWrapper;

    @FindBy(xpath = "//select[@name='geo']/following-sibling::div[contains(@class, 'choices')]")
    private WebElement geoWrapper;

    public void interactWithFilters() throws InterruptedException {
        categoryWrapper.click();
        Thread.sleep(500);
        categoryWrapper.click();
        geoWrapper.click();
        Thread.sleep(500);
        geoWrapper.click();
    }

    public void searchOffer(String offerName) {
        searchInput.clear();
        searchInput.sendKeys(offerName);
        searchBtn.click();
    }

    public void clickCreateFlowFor(String offerName) {
        String xpath = String.format(
                "//a[contains(@class, 'offer-title-new') and contains(text(), '%s')]" +
                        "/ancestor::div[contains(@class, 'offer-card-new')]" +
                        "//a[contains(@class, 'btn-primary')]",
                offerName
        );

        try {
            WebElement createBtn = driver.findElement(By.xpath(xpath));
            createBtn.click();
        } catch (Exception e) {
            System.out.println("ERROR: button create flow not found: " + offerName);
            throw e;
        }
    }
}