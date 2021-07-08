package com.codecool.giftservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GiftAsJson implements Serializable {
    private Long id;
    private String name;
    private List<Long> photosId;
    private Double value;
    private Long ownerId;
    private List<String> photoUrls;

    public GiftAsJson(Long id, String name, List<Long> photosId, Double value, Long ownerId, List<String> photoUrls) {
        this.id = id;
        this.name = name;
        this.photosId = photosId;
        this.value = value;
        this.ownerId = ownerId;
        this.photoUrls = photoUrls;
    }

    public GiftAsJson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getPhotosId() {
        return photosId;
    }

    public void setPhotosId(List<Long> photosId) {
        this.photosId = photosId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    @Override
    public String toString() {
        return "GiftAsJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photosId=" + photosId +
                ", value=" + value +
                ", ownerId=" + ownerId +
                ", photoUrls=" + photoUrls +
                '}';
    }
}
