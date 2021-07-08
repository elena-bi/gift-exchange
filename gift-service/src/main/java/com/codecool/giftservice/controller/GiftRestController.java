package com.codecool.giftservice.controller;

import com.codecool.giftservice.model.Gift;
import com.codecool.giftservice.model.GiftAsJson;
import com.codecool.giftservice.model.Image;
import com.codecool.giftservice.repository.GiftRepository;
import com.codecool.giftservice.repository.ImageRepository;
import com.codecool.giftservice.services.GiftUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GiftRestController {
    private GiftRepository giftRepository;
    private GiftUtils giftUtils;
    private ImageRepository imageRepo;

    @Autowired
    public GiftRestController(GiftRepository giftRepository, GiftUtils giftUtils, ImageRepository imageRepo) {
        this.giftRepository = giftRepository;
        this.giftUtils = giftUtils;
        this.imageRepo = imageRepo;
    }

    private Gift saveObjectsInDbFor(GiftAsJson gift, boolean overwriteMode) {
        List<Image> images = new ArrayList<>();
        for (Long imageId : gift.getPhotosId()) {
            images.add(new Image(imageId, null, null));
        }

        int counter = 0;
        for (String photoUrl : gift.getPhotoUrls()) {
            images.get(counter).setUrl(photoUrl);
            counter++;
        }

        Gift giftInDb;
        Gift newGift;
        if(overwriteMode) {
            giftInDb = new Gift(gift.getId(), gift.getName(), images, gift.getValue(), gift.getOwnerId());
            for (Image image : images) {
                image.setForGift(giftInDb);
            }
            for (Image image : images) {
                imageRepo.save(image);
            }
            newGift = giftRepository.save(giftInDb);
        } else{
            giftInDb = new Gift(gift.getName(), images, gift.getValue(), gift.getOwnerId());
            for (Image image : images) {
                image.setForGift(giftInDb);
            }

            newGift = giftRepository.save(giftInDb);
            for (Image image : images) {
                imageRepo.save(image);
            }
        }
        return newGift;
    }

    private void deleteAllImagesForGiftId(Long id) {
        List<Image> imagesToDelete = imageRepo.findAllByForGift_Id(id);
        for (Image image : imagesToDelete) {
            imageRepo.delete(image);
        }
    }

    @GetMapping("/gifts")
    public List<GiftAsJson> getGifts(){
        List<Gift> gifts = giftRepository.findAll();
        List<GiftAsJson> resultList = new ArrayList<>();


        for (Gift gift : gifts) {
            resultList.add(giftUtils.mapToJson(gift));
        }

        System.out.println(resultList);
        return resultList;
    }

    @GetMapping("/gift/{id}")
    public GiftAsJson getGiftById(@PathVariable("id") Long id){
        System.out.println(id);
        Gift gift = giftRepository.getById(id);
        GiftAsJson result = giftUtils.mapToJson(gift);
        return result;
    }

    @PostMapping("/gifts")
    public GiftAsJson addGifts(@RequestBody GiftAsJson gift){
        Gift newGift = saveObjectsInDbFor(gift, false);

        return giftUtils.mapToJson(newGift);
    }

    @PutMapping("/gift/{id}")
    public GiftAsJson replaceGift(@PathVariable Long id, @RequestBody GiftAsJson giftAsJson){
        giftAsJson.setId(id);
        deleteAllImagesForGiftId(id);
        Gift replaceGift = saveObjectsInDbFor(giftAsJson, true);
        return  giftUtils.mapToJson(replaceGift);
    }


    @DeleteMapping("/gift/{id}")
    public void deletePost(@PathVariable(value = "id") Long giftId){
        System.out.println( "Trying to delete " + giftRepository.getOne(giftId));
        deleteAllImagesForGiftId(giftId);
        giftRepository.deleteById(giftId);
        System.out.println(giftRepository.findAll());
    }

}
