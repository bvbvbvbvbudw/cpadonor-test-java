package com.cpadonor.ui;

import com.cpadonor.web.bo.DashboardBO;
import com.cpadonor.web.bo.LoginBO;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {
    @Test
    public void checkMenuLinks() {
        LoginBO loginBO = new LoginBO(driver);

        loginBO.login("batmansila1234@gmail.com", "batmansila1234");

        DashboardBO dashboardBO = new DashboardBO(driver);
        dashboardBO.checkAllMenuLinks();
        dashboardBO.checkProfileAndLogout();
    }
}