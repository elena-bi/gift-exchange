package com.codecool.giftservice.controller;

import com.codecool.giftservice.model.Gift;
import com.codecool.giftservice.repository.GiftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@Controller
public class GiftController {
    private GiftRepository giftRepository;

    @Autowired
    public GiftController(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }


    @GetMapping("/gifts")
    public String getGifts(Model model){
//        return giftRepository.findAll();
//        model.addAttribute("gifts", giftRepository.findAll());
        model.addAttribute("gifts", giftRepository.findAll());
        System.out.println(giftRepository.findAll());
        return "index";
    }

    @GetMapping("/gifts/{id}")
    public Gift getGiftById(@PathVariable("id") Long id){
        System.out.println(id);
        return giftRepository.getById(id);

    }

    @PostMapping("/gifts")
    public String addGifts(@Valid Gift gift, BindingResult result, Model model){
        if (result.hasErrors()) {
//            System.out.println("error");
//            System.out.println(user);
            return "";
        }
//        System.out.println(user);
        giftRepository.save(gift);
        return "redirect:/gifts";
    }

    @DeleteMapping("/gifts/{id}")
    public void deletePost(@PathVariable(value = "id") Long userId){
//            Gift giftToDelete = giftRepository.findById(userId)
//                    .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
//            System.out.println(giftToDelete.toString());
            System.out.println( "Trying to delete " + giftRepository.getById(userId));
            giftRepository.deleteById(userId);
            System.out.println(giftRepository.findAll().toString());
    }


}
