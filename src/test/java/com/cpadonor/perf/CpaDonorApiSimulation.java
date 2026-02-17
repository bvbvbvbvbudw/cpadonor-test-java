package com.cpadonor.perf;

import com.cpadonor.config.AppConfig;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class CpaDonorApiSimulation extends Simulation {

    private static int users() {
        return Integer.parseInt(System.getProperty("users", "1"));
    }

    private static int rampSeconds() {
        return Integer.parseInt(System.getProperty("rampSeconds", "10"));
    }

    private static String flowHash() {
        return System.getProperty("flowHash", "d7VnVjPMavuP");
    }

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl(AppConfig.apiBaseUrl())
            .contentTypeHeader("application/json")
            .acceptHeader("application/json")
            .userAgentHeader("gatling")
            .header("X-Flow-Domain", "cpadonor.online");

    private final Iterator<Map<String, Object>> clickFeeder = Stream.generate(() ->
            Map.<String, Object>of(
                    "clickId", UUID.randomUUID().toString(),
                    "flowHash", flowHash()
            )
    ).iterator();

    // Scenario 1: Click registration
    private final ScenarioBuilder registerClick = scenario("API: register click")
            .feed(clickFeeder)
            .exec(
                    http("POST /clicks/register")
                            .post("/clicks/register")
                            .body(StringBody(session ->
                                    String.format("{\"click_id\":\"%s\",\"flow_hash\":\"%s\"}",
                                            session.getString("clickId"),
                                            session.getString("flowHash")
                                    )))
                            .check(status().is(201))
            );

    // Scenario 2: Flow-data read endpoint
    private final ScenarioBuilder getFlowData = scenario("API: get flow data")
            .exec(
                    http("GET /flow-data/{hash}")
                            .get(session -> "/flow-data/" + flowHash())
                            .check(status().is(200))
            );

    // Scenario 3: Mixed user journey
    private final ScenarioBuilder userJourney = scenario("API: user journey")
            .feed(clickFeeder)
            .exec(
                    http("POST /clicks/register")
                            .post("/clicks/register")
                            .body(StringBody(session ->
                                    String.format("{\"click_id\":\"%s\",\"flow_hash\":\"%s\"}",
                                            session.getString("clickId"),
                                            session.getString("flowHash")
                                    )))
                            .check(status().is(201))
            )
            .pause(Duration.ofMillis(300))
            .exec(
                    http("GET /flow-data/{hash}")
                            .get(session -> "/flow-data/" + session.getString("flowHash"))
                            .check(status().is(200))
            );

    {
        setUp(
                registerClick.injectOpen(rampUsers(users()).during(Duration.ofSeconds(rampSeconds()))),
                getFlowData.injectOpen(rampUsers(users()).during(Duration.ofSeconds(rampSeconds()))),
                userJourney.injectOpen(rampUsers(users()).during(Duration.ofSeconds(rampSeconds())))
        ).protocols(httpProtocol);
    }
}