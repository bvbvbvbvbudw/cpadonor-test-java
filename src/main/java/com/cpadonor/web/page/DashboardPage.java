package com.cpadonor.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href$='dashboard']")
    private WebElement linkHome;

    @FindBy(css = "a[href*='dashboard/offers']")
    private WebElement linkOffers;

    @FindBy(css = "a[href*='flows']")
    private WebElement linkFlows;

    @FindBy(css = "a[href*='sources']")
    private WebElement linkSources;

    @FindBy(css = "a[href*='leads']")
    private WebElement linkLeads;

    @FindBy(css = "a[href*='bumps']")
    private WebElement linkBumps;

    @FindBy(css = "a[href*='payouts']")
    private WebElement linkPayouts;

    @FindBy(css = "a[href*='news']")
    private WebElement linkNews;

    @FindBy(css = "a[href*='notifications']")
    private WebElement linkNotifications;

    @FindBy(css = "a[href*='faq']")
    private WebElement linkFaq;

    @FindBy(css = "a[href*='t.me']")
    private WebElement linkSupportTg;


    @FindBy(id = "userMenuDropdown")
    private WebElement userMenuBtn;

    @FindBy(css = "a[href*='profile']")
    private WebElement linkProfile;

    @FindBy(css = "a[href*='domains']")
    private WebElement linkDomains;

    @FindBy(css = "a[href*='referrals']")
    private WebElement linkReferrals;

    @FindBy(css = "a[href*='logout']")
    private WebElement linkLogout;

    public void goHome() { linkHome.click(); }
    public void goOffers() { linkOffers.click(); }
    public void goFlows() { linkFlows.click(); }
    public void goDomains() { linkDomains.click(); }
    public void goSources() { linkSources.click(); }
    public void goLeads() { linkLeads.click(); }
    public void goBumps() { linkBumps.click(); }
    public void goPayouts() { linkPayouts.click(); }
    public void goNews() { linkNews.click(); }
    public void goNotifications() { linkNotifications.click(); }
    public void goFaq() { linkFaq.click(); }

    public String getSupportLink() {
        return linkSupportTg.getAttribute("href");
    }

    public void openUserMenu() {
        userMenuBtn.click();
    }

    public void goProfile() {
        if (!linkProfile.isDisplayed()) {
            openUserMenu();
        }
        linkProfile.click();
    }

    public void goReferrals() {
        if (!linkReferrals.isDisplayed()) {
            openUserMenu();
        }
        linkReferrals.click();
    }

    public void doLogout() {
        if (!linkLogout.isDisplayed()) {
            openUserMenu();
        }
        linkLogout.click();
    }
}