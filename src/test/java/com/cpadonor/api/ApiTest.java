package com.cpadonor.api;

import io.restassured.response.Response;
import com.cpadonor.api.dto.ClickDto;
import com.cpadonor.api.dto.LeadDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

public class ApiTest {

    private final String VALID_FLOW_HASH = "d7VnVjPMavuP";
    private final Integer VALID_FLOW_ID = 1321;

    @Test(description = "Full cycle: Click -> Wait -> Lead")
    public void checkRegistration() throws InterruptedException {
        ApiService api = new ApiService();
        String clickId = UUID.randomUUID().toString();

        Response clickResponse = api.registerClick(new ClickDto(clickId, VALID_FLOW_HASH));

        Assert.assertEquals(clickResponse.getStatusCode(), 201);
        Assert.assertEquals(clickResponse.jsonPath().getString("status"), "success");
        // Assert.assertNotNull(clickResponse.jsonPath().getString("target_url"), "Target URL is null!");
        // тимчасово відключено, так як на проекті перевірка з якого домену прийшов лід
//        Response leadResponse = api.registerUser(new LeadDto(clickId, "Ivan", "i@test.com", "123"));
//
//        Assert.assertEquals(leadResponse.getStatusCode(), 201);
//        Assert.assertNotNull(leadResponse.jsonPath().get("lead_id"), "Lead ID is missing!");
    }

    @Test(description = "Negative test: Fake click ID")
    public void checkRegistrationBadData() {
        ApiService api = new ApiService();
        Response response = api.registerUser(new LeadDto("fake-id", "Hacker", "h@test.com", "000"));
        Assert.assertNotEquals(response.getStatusCode(), 201);
    }

    @Test(description = "Get Flow Data (3rd scenario)")
    public void checkGetFlowData() {
        ApiService api = new ApiService();
        Response response = api.getFlowData(VALID_FLOW_HASH);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("flow_id"), VALID_FLOW_ID);
    }
}