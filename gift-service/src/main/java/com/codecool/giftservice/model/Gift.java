package com.codecool.giftservice.model;

//import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "gift")
public class Gift {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    private String name;
    private String image;
    private Double value;
//    private User owner;


    public Gift(Long id, String name, String image, Double value) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.value = value;
    }

    public Gift(String name, String image, Double value) {
        this.name = name;
        this.image = image;
        this.value = value;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gift)) return false;
        Gift gift = (Gift) o;
        return Objects.equals(id, gift.id) && Objects.equals(name, gift.name) && Objects.equals(image, gift.image) && Objects.equals(value, gift.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, value);
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", value=" + value +
                '}';
    }
}
