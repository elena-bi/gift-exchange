package com.codecool.giftservice.init;

import com.codecool.giftservice.model.Gift;
import com.codecool.giftservice.model.Image;
import com.codecool.giftservice.repository.GiftRepository;
import com.codecool.giftservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class GiftDbInit implements CommandLineRunner {
    private GiftRepository giftRepo;
    private ImageRepository imageRepo;

    @Autowired
    public GiftDbInit(GiftRepository giftRepo, ImageRepository imageRepo) {
        this.giftRepo = giftRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Long user1 = 1L;
        Long user2 = 2L;

        Image image1 = new Image(1L, "https://www.idevice.ro/wp-content/uploads/2020/01/luna-imagini.jpg",
                null);
        Image image2 = new Image(2L, "https://agora.md/cdn/p//news/big/foto--imagini-microscopice--un-fotograf-din-polonia-realizeaza-fotografii-inedite-29556.jpg",
                null);
        Image image3 = new Image(3L, "https://agora.md/cdn/p//news/big/foto--imagini-microscopice--un-fotograf-din-polonia-realizeaza-fotografii-inedite-29556.jpg",
                null);


        Gift gift1 = new Gift("name", Collections.singletonList(image1), 200.0, user1);
        Gift gift2 = new Gift("Another gift", Collections.singletonList(image3) , 100.0, user2);
        Gift gift3 = new Gift("Third gift", Collections.singletonList(image2), 90.0, user1);

        image1.setForGift(gift1);
        image2.setForGift(gift3);
        image3.setForGift(gift2);

//        user1.addGift(gift1);
//        user1.addGift(gift3);
//        user2.addGift(gift2);

        giftRepo.save(gift1);
        giftRepo.save(gift2);
        giftRepo.save(gift3);

        imageRepo.save(image1);
        imageRepo.save(image2);
        imageRepo.save(image3);



    }
}
