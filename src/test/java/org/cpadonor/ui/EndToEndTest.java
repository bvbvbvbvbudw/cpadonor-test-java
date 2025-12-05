package org.cpadonor.ui;

import org.cpadonor.web.page.DashboardPage;
import org.cpadonor.web.page.FlowWizardPage;
import org.cpadonor.web.page.OffersPage;
import org.cpadonor.web.page.SourcesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseTest {

    @Test
    public void fullFlowCreationScenario() throws InterruptedException {
        login();

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.goSources();
        Thread.sleep(1000);

        SourcesPage sourcesPage = new SourcesPage(driver);
        String mySource = "push.house";

        if (!sourcesPage.isSourcePresent(mySource)) {
            System.out.println("LOG: create source...");
            sourcesPage.clickAddButton();
            sourcesPage.fillSourceForm(mySource, "https://push.house", "Auto created");
            sourcesPage.submitForm();
            Thread.sleep(2000);
        }

        dashboard.goOffers();
        Thread.sleep(1000);

        OffersPage offersPage = new OffersPage(driver);
        offersPage.interactWithFilters();

        String targetOffer = "Dating Survey - WW";
        offersPage.searchOffer(targetOffer);
        Thread.sleep(1500);

        offersPage.clickCreateFlowFor(targetOffer);
        Thread.sleep(1000);

        Assert.assertTrue(driver.getCurrentUrl().contains("step-1"), "error step 1");
        FlowWizardPage wizard = new FlowWizardPage(driver);

        String flowName = "AutoFlow " + System.currentTimeMillis();
        wizard.fillStep1(flowName, mySource);
        System.out.println("LOG: step 1 done");

        Thread.sleep(1000);
        wizard.selectFirstLanding();
        System.out.println("LOG: step 2 done");

        Thread.sleep(1000);
        wizard.fillStep3();
        System.out.println("LOG: step 3 done");

        Thread.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("flows"), "error redirect");
        Assert.assertTrue(driver.getPageSource().contains(flowName), "error flow");
    }
}
// драйвер отримуати на рівні page