package com.example.imageservice.controllers;

import com.example.imageservice.controllers.exceptions.ImageNotFoundException;
import com.example.imageservice.model.Image;
import com.example.imageservice.repositories.ImageRepository;
import com.example.imageservice.services.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ImageBasicController {
    private ImageRepository imageRepo;

    @Autowired
    public ImageBasicController(ImageRepository imageRepo) {
        this.imageRepo = imageRepo;
    }

    @GetMapping("/photo/{id}")
    public RedirectView localRedirect(@PathVariable Long id) throws ImageNotFoundException {
        Image maybeImage = imageRepo.findById(id).orElseThrow(
                () -> new ImageNotFoundException("Image with id " + id + "not found")
        );
        RedirectView redirectView = new RedirectView();
        String photoUrl = maybeImage.getUrlString();
        redirectView.setUrl(photoUrl);
        return redirectView;
    }


}
