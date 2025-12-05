package org.cpadonor.api.dto;

import java.util.Random;

public class ClickDto {
    public String click_id;
    public String flow_hash;
    public Integer offer_id;
    public Integer domain_id;
    public String ip_address;
    public String user_agent;

    public ClickDto() {}

    public ClickDto(String click_id, String flow_hash) {
        this.click_id = click_id;
        this.flow_hash = flow_hash;
        this.offer_id = 1;
        this.domain_id = 1;
        this.ip_address = getRandomIp();
        this.user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
    }

    private String getRandomIp() {
        Random r = new Random();
        return r.nextInt(256) + "." +
                r.nextInt(256) + "." +
                r.nextInt(256) + "." +
                r.nextInt(256);
    }
}