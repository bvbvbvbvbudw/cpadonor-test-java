package com.cpadonor.api.dto;

public class LeadDto {
    public String click_id;
    public String name;
    public String email;
    public String phone;
    public String status;

    public LeadDto() {}

    public LeadDto(String click_id, String name, String email, String phone) {
        this.click_id = click_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = "pending";
    }
}