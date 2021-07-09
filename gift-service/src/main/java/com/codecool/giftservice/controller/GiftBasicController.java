package com.codecool.giftservice.controller;

import com.codecool.giftservice.model.Gift;
import com.codecool.giftservice.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class GiftBasicController {
    private GiftRepository giftRepository;

    @Autowired
    public GiftBasicController(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }


    @GetMapping("/")
    public String getGifts(Model model){
//        return giftRepository.findAll();
//        model.addAttribute("gifts", giftRepository.findAll());
        model.addAttribute("gifts", giftRepository.findAll());
        System.out.println(giftRepository.findAll());
        return "index";
    }




}
