package com.example.imageservice.config;


import com.example.imageservice.model.Image;
import com.example.imageservice.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseInit implements CommandLineRunner {
    private ImageRepository imageRepo;

    @Autowired
    public DatabaseInit(ImageRepository imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Image image1 = new Image(1L, "https://static.toiimg.com/thumb/msid-67586673,width-800,height-600,resizemode-75,imgsize-3918697,pt-32,y_pad-40/67586673.jpg");
        Image image2 = new Image(2L, "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2021_22/3479356/210601-main-pups-canine-companions-for-independence-ew-113p.jpg");
        Image image3 = new Image(3L, "https://image.shutterstock.com/image-photo/little-yellow-ducklings-on-hay-260nw-1182470968.jpg");
        imageRepo.saveAll(Arrays.asList(image1,image2,image3));
    }
}
