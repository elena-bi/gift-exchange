package com.codecool.giftservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    private Long id;
    private String url;

    @ManyToOne
    @JoinColumn(name="giftId")
    @JsonBackReference
    private Gift forGift;

    public Image(Long id, String url, Gift forGift) {
        this.id = id;
        this.url = url;
        this.forGift = forGift;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Gift getForGift() {
        return forGift;
    }

    public void setForGift(Gift forGift) {
        this.forGift = forGift;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", forGift=" + forGift +
                '}';
    }
}
