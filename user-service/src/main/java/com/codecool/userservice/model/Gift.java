package com.codecool.userservice.model;

public class Gift {

    private final Long id;
    private final String name;
    private final Long photoId;
    private final Double value;
    private User owner;

    public Gift(Long id, String name, Long photoId, Double value, User owner) {
        this.id = id;
        this.name = name;
        this.photoId = photoId;
        this.value = value;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public Double getValue() {
        return value;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
