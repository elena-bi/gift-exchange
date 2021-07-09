package com.codecool.userservice.controller;

import com.codecool.userservice.controller.exceptions.GiftNotFoundInDBException;
import com.codecool.userservice.controller.exceptions.UserAlreadyPresentInDatabaseException;
import com.codecool.userservice.controller.exceptions.UserNotFoundInDBException;
import com.codecool.userservice.model.*;
import com.codecool.userservice.repositories.GiftRepository;
import com.codecool.userservice.repositories.UserRepository;
import com.codecool.userservice.services.UserUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserRepository userRepo;
    private UserUtils userUtils;
    private GiftRepository giftRepo;

    @Autowired
    public UserController(UserRepository userRepo, UserUtils userUtils, GiftRepository giftRepo) {
        this.userRepo = userRepo;
        this.userUtils = userUtils;
        this.giftRepo = giftRepo;
    }

    @PostMapping("/register")
    public User handleUserRegistration(@RequestBody UserBasicInfo basicInfo) {
        User newUser = userUtils.mapFromBasicInfo(basicInfo);
        User shouldBeNullUser = userRepo.findUserByUsername(newUser.getUsername());

        if (shouldBeNullUser != null) {
            throw new UserAlreadyPresentInDatabaseException();
        }

        return userRepo.save(newUser);
    }

    @GetMapping("/users")
    public List<UserPublicInfo> handleGetAllUsers() {
        List<User> allUsers = userRepo.findAll();
        List<UserPublicInfo> allPublicInfo = allUsers.stream()
                .map(user -> userUtils.mapUserToPublicInfo(user))
                .collect(Collectors.toList());
        return allPublicInfo;
    }

    @GetMapping("/user/{id}")
    public UserPublicInfo handleGetPublicInfoForOneUser(@PathVariable Long id) {
        User maybeUser = userRepo.findById(id).orElseThrow(UserNotFoundInDBException::new);
        return userUtils.mapUserToPublicInfo(maybeUser);
    }

    @GetMapping("/user_authenticated/{id}")
    public User handleGetOneUser(@PathVariable Long id) {
        User maybeUser = userRepo.findById(id).orElseThrow(UserNotFoundInDBException::new);
        return maybeUser;
    }

    @GetMapping("/owner/for/{giftId}")
    public UserPublicInfo getPublicInfoForOwnerOfGift(@PathVariable Long giftId) {
        Gift maybeGift = giftRepo.findById(giftId).orElseThrow(GiftNotFoundInDBException::new);
        if (maybeGift.getState() == GiftState.TO_RECEIVE) {
            throw new GiftNotFoundInDBException();
        }
        User maybeUser = userRepo.findUserByGiftsContains(maybeGift);
        if (maybeUser == null) {
            throw new UserNotFoundInDBException();
        }
        return userUtils.mapUserToPublicInfo(maybeUser);
    }

    @GetMapping("/test/{user}")
    public String getUser(@PathVariable String user) {
        User newUser = userRepo.findUserByUsername(user);
        if (newUser != null) {
            return newUser.toString();
        } else {
            return "null";
        }
    }

}
