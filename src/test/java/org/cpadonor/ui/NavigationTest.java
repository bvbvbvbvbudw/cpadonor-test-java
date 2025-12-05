package org.cpadonor.ui;

import org.cpadonor.web.page.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void checkMenuLinks() throws InterruptedException {
        login(); // todo перенести в бизнес об.

        // перенести в checkallpages
        DashboardPage dash = new DashboardPage(driver);
        checkPage(dash::goOffers, "offers");
        checkPage(dash::goFlows, "flows");
        checkPage(dash::goSources, "sources");
        checkPage(dash::goLeads, "leads");
        checkPage(dash::goBumps, "bumps");
        checkPage(dash::goPayouts, "payouts");
        checkPage(dash::goNews, "news");
        checkPage(dash::goNotifications, "notifications");
        checkPage(dash::goFaq, "faq");

        dash.goProfile();
        Assert.assertTrue(driver.getCurrentUrl().contains("profile"));

        dash.doLogout();
        Thread.sleep(1000);
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    private void checkPage(Runnable action, String urlPart) throws InterruptedException {
        action.run();
        Thread.sleep(1000); // замінити на waiter
        Assert.assertTrue(driver.getCurrentUrl().contains(urlPart), "page -  " + urlPart + " error");
    }
}