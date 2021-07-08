package com.codecool.giftservice.model;

//import org.hibernate.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "gift")
public class Gift implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy="forGift")
    @JsonManagedReference
    private List<Image> images;
    private Double value;
    private Long ownerId;


    public Gift(Long id, String name, List<Image> images, Double value, Long owner) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.value = value;
        this.ownerId = owner;
    }

    public Gift(String name, List<Image> images, Double value, Long owner) {
        this.name = name;
        this.images = images;
        this.value = value;
        this.ownerId = owner;
    }

    public Gift() {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        images.add(image);
    }


    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
