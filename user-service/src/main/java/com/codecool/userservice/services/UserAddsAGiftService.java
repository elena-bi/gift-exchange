package com.codecool.userservice.services;

import com.codecool.userservice.model.Image;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAddsAGiftService {

    // get userId for currently logged-in user
    // for each
    // [FE: user clicks upload image button]
    // POST to a discovered client (DC) which has a /images route
    // that route returns a JSON with an imageId and and imageUrl
    // end for each
    // collect all imageId and imageUrls in 2 lists (List<Long> & List<String>)
    // [FE: user fills in fields for a Gift (in a form)]
    // create a GiftAsJson object with name, value from form
    // [FE: user clicks submit on form]
    // set imageIds and ImageUrls for that object from the 2 lists; set the ownerId in the same object
    // POST to a DC which has a /gifts route with that GiftAsJson object in the RequestBody


    // get userId for currently logged-in user
    // for each
    // POST to a discovered client (DC) which has a /images route
    // that route returns a JSON with an imageId and and imageUrl
    // end for each
    // collect all imageId and imageUrls in 2 lists (List<Long> & List<String>)
    // create a GiftAsJson object with name, value from form
    // set imageIds and ImageUrls for that object from the 2 lists; set the ownerId in the same object
    // POST to a DC which has a /gifts route with that GiftAsJson object in the RequestBody



    private EurekaClient eurekaClient;

    @Autowired
    public UserAddsAGiftService(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    public Image getAnImageForId(Long imageId) {
        InstanceInfo serviceInstance = eurekaClient
                .getApplication("image-service")
                .getInstances()
                .get(0);

        String hostName = serviceInstance.getHostName();
        int port = serviceInstance.getPort();

        RestTemplate restTemplate = new RestTemplate();
        String imageUrl = "http://"+hostName + ":" + port + "/image/" + imageId;
        Image gottenImage = new Image(-1L, "--");

        try {
            System.out.println(imageUrl);
            gottenImage = restTemplate.getForObject(imageUrl, Image.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gottenImage;
    }

    public boolean deleteGiftById(Long giftId) {
        InstanceInfo serviceInstance = eurekaClient
                .getApplication("gift-service")
                .getInstances()
                .get(0);

        String hostName = serviceInstance.getHostName();
        int port = serviceInstance.getPort();

        RestTemplate restTemplate = new RestTemplate();
        String giftUrl = "http://"+hostName + ":" + port + "/gift/" + giftId;

        boolean result = true;
        try {
            System.out.println(giftUrl);
            restTemplate.delete(giftUrl);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }


}
