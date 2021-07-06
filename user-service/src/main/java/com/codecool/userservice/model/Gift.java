package com.codecool.userservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String name;

    @Transient
    private Long photoId;

    @Transient
    private Double value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User owner;

    @Enumerated
    private GiftState state;

    public Gift(Long id, String name, Long photoId, Double value, User owner) {
        this.id = id;
        this.name = name;
        this.photoId = photoId;
        this.value = value;
        this.owner = owner;
        this.state = GiftState.TO_SELL;
    }

    protected Gift() {
        this.state = GiftState.UNDER_CONSTRUCTION;
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

    public GiftState getState() {
        return state;
    }

    public void setState(GiftState state) {
        this.state = state;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
