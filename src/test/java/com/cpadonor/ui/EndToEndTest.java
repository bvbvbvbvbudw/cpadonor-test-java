package com.cpadonor.ui;

import com.cpadonor.web.bo.FlowBO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseTest {

    @Test
    public void fullFlowCreationScenario() {
        login();

        FlowBO flowBO = new FlowBO(driver);

        String mySource = "google.com";
        String targetOffer = "Motion Energy (PE)";

        flowBO.ensureSourceExists(mySource, "https://google.com");

        flowBO.startFlowCreation(targetOffer);

        String flowName = "AutoFlow " + System.currentTimeMillis();
        flowBO.passWizardStep1(flowName, mySource);
        flowBO.passWizardStep2();
        flowBO.passWizardStep3();

        Assert.assertTrue(driver.getCurrentUrl().contains("flows"), "Error: Не перекинуло на список потоків");
        Assert.assertTrue(flowBO.isFlowInList(flowName), "Error: Потік не знайдено в списку");
    }
}