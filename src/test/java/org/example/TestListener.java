package org.example;

import com.cpadonor.ui.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        attachStuff(result, "Failed");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachStuff(result, "Success");
    }

    private void attachStuff(ITestResult result, String status) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) currentClass).driver;

            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Page Screenshot (" + status + ")", new ByteArrayInputStream(screenshot));
                Allure.addAttachment("Current URL", "text/plain", driver.getCurrentUrl());
                // Allure.addAttachment("Page Source", "text/html", driver.getPageSource());
            }
        }
    }
}