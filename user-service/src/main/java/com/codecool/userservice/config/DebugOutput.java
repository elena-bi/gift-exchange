package com.codecool.userservice.config;

import com.codecool.userservice.services.UserAddsAGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DebugOutput implements CommandLineRunner {

    @Autowired
    private UserAddsAGiftService testService;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(testService.getAnImageForId(1L));
//        System.out.println(testService.deleteGiftById(1L));
    }
}
