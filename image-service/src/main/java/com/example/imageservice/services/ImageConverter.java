package com.example.imageservice.services;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@Service
public class ImageConverter {

    public ImageConverter() {
    }

    public String convertToByteString(String filePath) {
        byte[] imageContent;
        String encodedImageString = "";
        try {
            imageContent = FileUtils.readFileToByteArray(new File(filePath));
            encodedImageString = Base64.getEncoder().encodeToString(imageContent);
        } catch (IOException e) {
            System.out.println("An error occurred when attempting to convert image from " + filePath);
        }

        return encodedImageString;
    }

    public String getURLStringForBytes(String encodedImageInBytes, Long id) {
        String serverPathName = "/static/" + "image" + id;
        String filePathName = "." + serverPathName;
        byte[] decodedImage = Base64.getDecoder().decode(encodedImageInBytes);
        try {
            FileUtils.writeByteArrayToFile(new File(filePathName), decodedImage);
        } catch (IOException e) {
            System.out.println("Error while decoding image ");
        }
        String serverAddress = "http://localhost:8080";

        return serverAddress + serverPathName;
    }

    public String getURLString(String seed, Long id) {
        int nrImages = 10;
        Random randomizer = new Random();
        randomizer.setSeed((seed+id).hashCode());
        switch (randomizer.nextInt() % nrImages) {
            case 0: return "https://upload.wikimedia.org/wikipedia/commons/2/20/Amazon.com_Gift_Cards_in_hand.jpg";
            case 1: return "https://static.toiimg.com/thumb/msid-67586673,width-800,height-600,resizemode-75,imgsize-3918697,pt-32,y_pad-40/67586673.jpg";
            case 2: return "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2021_22/3479356/210601-main-pups-canine-companions-for-independence-ew-113p.jpg";
            case 3: return "https://image.shutterstock.com/image-photo/little-yellow-ducklings-on-hay-260nw-1182470968.jpg";
            case 4: return "https://www.gannett-cdn.com/-mm-/7cfa0cdce1b602172c2e2961197d42679ab2b3cc/c=0-278-2520-1702/local/-/media/2017/01/28/USATODAY/USATODAY/636211875995222622-GettyImages-462548187.jpg";
            case 5: return "https://inhabitat.com/wp-content/blogs.dir/1/files/2014/12/Baby-Elephant-600x480.jpg";
            case 6: return "https://media.istockphoto.com/photos/slumbering-little-tiger-picture-id472298390?k=6&m=472298390&s=612x612&w=0&h=jxLu6DeRBWpvofFt3ZtwdQjP-neDZhlJGoNO_pl-BRs=";
            case 7: return "https://www.independent.co.uk/s3fs-public/thumbnails/image/2019/02/18/08/polar-bear-cub-1.jpg?width=1200&auto=webp&quality=75";
            case 8: return "https://static.dw.com/image/43936846_303.jpg";
            case 9: return "https://www.indianapoliszoo.com/wp-content/uploads/2019/08/Dolphin-Calf-Banner.jpg";
            default: return "https://imagesvc.meredithcorp.io/v3/mm/image?q=85&c=sc&poi=%5B1000%2C562%5D&w=2000&h=1000&url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F24%2F2018%2F11%2Fgettyimages-962721850-2000.jpg";
        }


    }
}
