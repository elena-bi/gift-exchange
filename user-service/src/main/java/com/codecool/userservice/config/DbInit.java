package com.codecool.userservice.config;

import com.codecool.userservice.model.Gift;
import com.codecool.userservice.model.User;
import com.codecool.userservice.model.UserRole;
import com.codecool.userservice.repositories.GiftRepository;
import com.codecool.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Component
public class DbInit implements CommandLineRunner {

    private UserRepository userRepo;
    private GiftRepository giftRepo;

    @Autowired
    public DbInit(UserRepository userRepo, GiftRepository giftRepo) {
        this.userRepo = userRepo;
        this.giftRepo = giftRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User(1L,
                "Admin",
                "ForTheRepublic2021",
                Collections.singleton(UserRole.ROLE_ADMIN),
                "admin@gift_exchange.com"
        );
        User testUser = new User(
                2L,
                "testUser",
                "123",
                Collections.singleton(UserRole.ROLE_USER),
                "testUser@test.test"
        );

        Gift gift1 = new Gift(1L,"name", 1L, 200.0, admin);
        Gift gift2 = new Gift(2L,"Another gift", 3L , 100.0, testUser);
        Gift gift3 = new Gift(3L, "Third gift", 2L, 90.0, admin);
        Gift gift4 = new Gift(1004L, "Gift to receive", 1L, 1000.0, testUser);

        admin.addGiftToSell(gift1);
        testUser.addGiftToSell(gift2);
        admin.addGiftToSell(gift3);
        testUser.addGiftToReceive(gift4);

//        userRepo.saveAll(Arrays.asList(admin, testUser));
//        giftRepo.saveAll(Arrays.asList(gift1, gift2, gift3, gift4));
    }
}
