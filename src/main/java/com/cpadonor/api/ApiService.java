package com.cpadonor.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.cpadonor.api.dto.ClickDto;
import com.cpadonor.api.dto.LeadDto;

public class ApiService {

    private final String BASE_API_URL = "https://api.cpadonor.com/api";

    public Response registerClick(ClickDto click) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(click)
                .post(BASE_API_URL + "/clicks/register");
    }

    public Response registerUser(LeadDto lead) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(lead)
                .post(BASE_API_URL + "/leads/register");
    }

    public Response getFlowData(String hash) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .get(BASE_API_URL + "/flow-data/" + hash);
    }
}