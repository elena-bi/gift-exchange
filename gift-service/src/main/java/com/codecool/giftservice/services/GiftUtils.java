package com.codecool.giftservice.services;

import com.codecool.giftservice.model.Gift;
import com.codecool.giftservice.model.GiftAsJson;
import com.codecool.giftservice.model.Image;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftUtils {
    public GiftUtils() {
    }

    public GiftAsJson mapToJson(Gift gift){
        Long id = gift.getId();
        String name = gift.getName();
        Double value = gift.getValue();
        Long ownerId = gift.getOwnerId();
        List<String> urls = new ArrayList<>();
        List<Long> imageIds = new ArrayList<>();
        for (Image image  : gift.getImages()) {
            urls.add(image.getUrl());
            imageIds.add(image.getId());
        }

        return new GiftAsJson(id, name, imageIds, value, ownerId, urls);
    }
}
