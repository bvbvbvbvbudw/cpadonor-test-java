package org.cpadonor.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.cpadonor.api.dto.ClickDto;
import org.cpadonor.api.dto.LeadDto;

public class ApiService {

    private final String BASE_API_URL = "http://localhost:8000/api";

    public Response registerClick(ClickDto click) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(click)
                .post(BASE_API_URL + "/clicks/register");
    }

    public Response registerUser(LeadDto lead) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(lead)
                .post(BASE_API_URL + "/leads/register");
    }
}