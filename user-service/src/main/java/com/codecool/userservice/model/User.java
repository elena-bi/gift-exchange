package com.codecool.userservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Set<UserRole> roles;

    private String email;
    private String address;
    private double credit;
    private Set<Gift> giftsToSell;
    private Set<Gift> giftsToReceive;

    public User(Long id, String username, String password, Set<UserRole> roles, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
        this.address = "";
        this.credit = 0.0;
        this.giftsToReceive = new HashSet<>();
        this.giftsToSell = new HashSet<>();
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
        return new HashSet<>(giftsToSell);
    }

    public void addGiftToSell(Gift gift) {
        this.giftsToSell.add(gift);
    }

    public void removeGiftToSell(Gift gift) {
        this.giftsToSell.remove(gift);

    }

    public Set<Gift> getGiftsToReceive() {
        return new HashSet<>(giftsToReceive);
    }

    public void addGiftToReceive(Gift gift) {
        this.giftsToReceive.add(gift);
    }

    public void removeGiftToReceive(Gift gift) {
        this.giftsToReceive.remove(gift);
    }
}
