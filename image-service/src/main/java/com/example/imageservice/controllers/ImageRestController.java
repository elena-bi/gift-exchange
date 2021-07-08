package com.example.imageservice.controllers;

import com.example.imageservice.controllers.exceptions.ImageNotFoundException;
import com.example.imageservice.model.Image;
import com.example.imageservice.repositories.ImageRepository;
import com.example.imageservice.services.ImageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ImageRestController {
    private ImageRepository imageRepo;
    private ImageConverter imageConverter;

    public ImageRestController(ImageRepository imageRepo, ImageConverter imageConverter) {
        this.imageRepo = imageRepo;
        this.imageConverter = imageConverter;
    }

    @GetMapping("/image/{id}")
    public Image handleGetImageAsJson(@PathVariable Long id) {
        Image maybeImage = imageRepo.findById(id).orElseThrow(
                () -> new ImageNotFoundException("Image with id " + id + "not found")
        );
        return maybeImage;
    }

    @PostMapping("/images")
    public Image postNewImage(@RequestBody String imageData) {
        Image imageWithId = imageRepo.save(new Image());
        Long imageId = imageWithId.getId();
        String imageURL = imageConverter.getURLString(imageData,imageId);
        imageWithId.setUrlString(imageURL);
        imageRepo.save(imageWithId);
        return imageWithId;
    }

    @PutMapping("/image/{id}")
    public Image replaceImage(@RequestBody String imageData, @PathVariable Long id) {
        Optional<Image> maybeImage = imageRepo.findById(id);
        String newUrl = imageConverter.getURLString(imageData,id);
        Image replacedImage = new Image(id, newUrl);
        imageRepo.save(replacedImage);
        return replacedImage;
        //If we were not able to update by calling save with a new object
        // we would need to call the setter on the old object like so
//        Image imageToSave = maybeImage.map(image -> {
//            image.setUrlString(newUrl);
//            return image;
//        }).orElse(replacedImage);
//        imageRepo.save(imageToSave);
    }
}
