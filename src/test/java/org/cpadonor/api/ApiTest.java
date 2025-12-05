package org.cpadonor.api;

import io.restassured.response.Response;
import org.cpadonor.api.dto.ClickDto;
import org.cpadonor.api.dto.LeadDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class ApiTest {

    @Test
    public void checkRegistration() throws InterruptedException {
        ApiService api = new ApiService();
        String clickId = UUID.randomUUID().toString();
        String VALID_FLOW_HASH = "QLsTC6ZHaYTf";
        Response clickResponse = api.registerClick(new ClickDto(clickId, VALID_FLOW_HASH));
        Assert.assertEquals(clickResponse.getStatusCode(), 201);
// todo валидація якогось поля
        // Thread.sleep(3000); // fraud delay
// todo валидація якогось поля
        Response leadResponse = api.registerUser(new LeadDto(clickId, "Ivan", "i@test.com", "123"));
        Assert.assertEquals(leadResponse.getStatusCode(), 201);
    }
// todo доробити всі апі
    @Test
    public void checkRegistrationBadData() {
        ApiService api = new ApiService();
        Response response = api.registerUser(new LeadDto("fake-id", "Hacker", "h@test.com", "000"));
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}