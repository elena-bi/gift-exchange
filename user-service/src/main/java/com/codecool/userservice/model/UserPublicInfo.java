package com.codecool.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPublicInfo {

    private Long id;

    private String username;


    private double credit;

    private List<Gift> giftsToSell;

    public UserPublicInfo(Long id, String username, double credit, List<Gift> giftsToSell) {
        this.id = id;
        this.username = username;
        this.credit = credit;
        this.giftsToSell = giftsToSell;
    }

    public UserPublicInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public List<Gift> getGiftsToSell() {
        return giftsToSell;
    }

    public void setGiftsToSell(List<Gift> giftsToSell) {
        this.giftsToSell = giftsToSell;
    }
}
