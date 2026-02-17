package com.cpadonor.web.bo;

import com.cpadonor.web.page.DashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class DashboardBO {
    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashPage;

    public DashboardBO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.dashPage = new DashboardPage(driver);
    }

    public void checkAllMenuLinks() {
        checkPage(dashPage::goOffers, "offers");
        checkPage(dashPage::goFlows, "flows");
        checkPage(dashPage::goDomains, "domains");
        checkPage(dashPage::goSources, "sources");
        checkPage(dashPage::goLeads, "leads");
        checkPage(dashPage::goBumps, "bumps");
        checkPage(dashPage::goPayouts, "payouts");
        checkPage(dashPage::goNews, "news");
        checkPage(dashPage::goNotifications, "notifications");
        checkPage(dashPage::goFaq, "faq");
    }

    public void checkProfileAndLogout() {
        dashPage.goProfile();
        wait.until(ExpectedConditions.urlContains("profile"));
        Assert.assertTrue(driver.getCurrentUrl().contains("profile"), "error profile");
        dashPage.doLogout();
        wait.until(ExpectedConditions.urlContains("en"));
        Assert.assertTrue(driver.getCurrentUrl().contains("en"), "error logout");
    }

    private void checkPage(Runnable action, String urlPart) {
        action.run();
        try {
            wait.until(ExpectedConditions.urlContains(urlPart));
        } catch (Exception e) {
            Assert.fail("Page load timeout or error. Expected URL to contain: " + urlPart + ". Current: " + driver.getCurrentUrl());
        }
    }
}