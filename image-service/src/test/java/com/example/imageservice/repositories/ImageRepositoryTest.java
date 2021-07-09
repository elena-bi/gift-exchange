package com.example.imageservice.repositories;

import com.example.imageservice.model.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ImageRepositoryTest {
    private ImageRepository imageRepoInstance;

    @Autowired
    public ImageRepositoryTest(ImageRepository imageRepoInstance) {
        this.imageRepoInstance = imageRepoInstance;
    }
    @BeforeAll
    public void fillSomeDataInRepo() {
        Image image1= new Image(1L,"www.string1.com");
        Image image2 = new Image(2L, "www.google.com");
        imageRepoInstance.save(image1);
        imageRepoInstance.save(image2);
    }
    @Test
    public void shouldUpdateObjectWhenSavingNewInstanceWithSameId() {
//        arrange
            Image replacedImage = new Image(1L,"www.yahoo.com");
            Image oldImage = imageRepoInstance.findById(1L).orElseThrow(RuntimeException::new);
            String oldUrl = oldImage.getUrlString();

//        act
            imageRepoInstance.save(replacedImage);
            Image imageInRepo = imageRepoInstance.findById(1L).orElseThrow(RuntimeException::new);
            String newUrl = imageInRepo.getUrlString();
//        assert
            assertNotNull(oldUrl);
            assertNotEquals(oldUrl, newUrl);
            assertEquals("www.yahoo.com", newUrl);
    }


}