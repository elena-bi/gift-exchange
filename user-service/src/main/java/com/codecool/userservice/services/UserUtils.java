package com.codecool.userservice.services;

import com.codecool.userservice.model.User;
import com.codecool.userservice.model.UserBasicInfo;
import com.codecool.userservice.model.UserPublicInfo;
import com.codecool.userservice.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserUtils {

    public User mapFromBasicInfo(UserBasicInfo basicInfo) {
        return new User(
                null,
                basicInfo.getUsername(),
                basicInfo.getPassword(),
                Collections.singleton(UserRole.ROLE_USER),
                basicInfo.getEmail()
        );
    }

    public UserPublicInfo mapUserToPublicInfo(User user) {

        return new UserPublicInfo(
                user.getId(),
                user.getUsername(),
                user.getCredit(),
                new ArrayList<>(user.getGiftsToSell())
        );
    }
}
