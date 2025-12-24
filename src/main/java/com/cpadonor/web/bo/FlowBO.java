package com.cpadonor.web.bo;

import com.cpadonor.web.page.DashboardPage;
import com.cpadonor.web.page.FlowWizardPage;
import com.cpadonor.web.page.OffersPage;
import com.cpadonor.web.page.SourcesPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FlowBO {
    private WebDriver driver;
    private DashboardPage dashboard;
    private SourcesPage sourcesPage;
    private OffersPage offersPage;
    private FlowWizardPage wizardPage;

    public FlowBO(WebDriver driver) {
        this.driver = driver;
        this.dashboard = new DashboardPage(driver);
        this.sourcesPage = new SourcesPage(driver);
        this.offersPage = new OffersPage(driver);
        this.wizardPage = new FlowWizardPage(driver);
    }

    public void ensureSourceExists(String sourceName, String url) {
        dashboard.goSources();
        sleep(1000);

        if (!sourcesPage.isSourcePresent(sourceName)) {
            System.out.println("LOG: Creating source " + sourceName);
            sourcesPage.clickAddButton();
            try {
                sourcesPage.fillSourceForm(sourceName, url, "Auto created");
                sourcesPage.submitForm();
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startFlowCreation(String offerName) {
        dashboard.goOffers();
        sleep(1000);

        offersPage.searchOffer(offerName);
        sleep(1500);

        offersPage.clickCreateFlowFor(offerName);
        sleep(1000);
    }

    public void passWizardStep1(String flowName, String sourceName) {
        Assert.assertTrue(driver.getCurrentUrl().contains("step-1"), "Not on Step 1");
        try {
            wizardPage.fillStep1(flowName, sourceName);
            System.out.println("LOG: Step 1 done");
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void passWizardStep2() {
        wizardPage.selectFirstLanding();
        System.out.println("LOG: Step 2 done");
        sleep(1000);
    }

    public void passWizardStep3() {
        try {
            wizardPage.fillStep3();
            System.out.println("LOG: Step 3 done");
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlowInList(String flowName) {
        return driver.getPageSource().contains(flowName);
    }

    private void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}