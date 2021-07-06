package com.codecool.userservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "account")   // user is a special keyword in SQL
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "role_id")
    private Set<UserRole> roles;


    private String email;
    private String address;
    private double credit;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private Set<Gift> gifts;


    public User(Long id, String username, String password, Set<UserRole> roles, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
        this.address = "";
        this.credit = 0.0;
        this.gifts = new HashSet<>();
    }

    protected User() {
        this.roles = Collections.emptySet();
        this.address = "";
        this.credit = -0.000001;
        this.gifts = Collections.emptySet();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    // rest of overriden methods represent User security features not needed/not implemented
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // end of UserDetails overrides


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void addToCredit(double amount) {
        this.credit += amount;
    }

    public Set<Gift> getGiftsToSell() {
        return gifts.stream()
                .filter(gift -> gift.getState().equals(GiftState.TO_SELL))
                .collect(Collectors.toSet());
    }

    public void addGiftToSell(Gift gift) {
        gift.setOwner(this);
        gift.setState(GiftState.TO_SELL);
        this.gifts.add(gift);
    }

    public void removeGift(Gift gift) {
        this.gifts.remove(gift);

    }

    public Set<Gift> getGiftsToReceive() {
        return gifts.stream()
                .filter(gift -> gift.getState().equals(GiftState.TO_RECEIVE))
                .collect(Collectors.toSet());
    }

    public void addGiftToReceive(Gift gift) {
        gift.setOwner(this);
        gift.setState(GiftState.TO_RECEIVE);
        this.gifts.add(gift);
    }



    public void markGiftToSell(Gift gift) {
        if (gifts.contains(gift)) {
            gift.setState(GiftState.TO_SELL);
        }
    }
}
