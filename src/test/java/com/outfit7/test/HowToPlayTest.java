package com.outfit7.test;

import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;
import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HowToPlayTest extends BaseTest{

    /*
        Find a string inside How To Play info page.
        String you need to find is: "Talk to Tom and he repeats with a funny voice"
    */
    private static final Logger logger = LogManager.getLogger(HowToPlayTest.class);

    @Test
    void findElementForInfo() throws Exception{

        String containsParagraph = "Talk to Tom and he repeats with a funny voice";
        List<MobileElement> itemsOfAboutPage;

        // First we are going to open How to play page
        driver.findElement(By.id("com.outfit7.talkingtom:id/buttonInfo")).click();
        logger.info("Info button was pressed");
        Thread.sleep(1000);
        driver.findElement(By.id("com.outfit7.talkingtom:id/infoWebButtonHowToPlay")).click();
        logger.info("We are in HOT TO PLAY view");
        Thread.sleep(1000);

        // Text that is shown to user is inside ListView element -> android.view.View
        itemsOfAboutPage = driver.findElements(By.className("android.view.View"));

        // Go through all items that you have collected and find your matching string
        boolean match = itemsOfAboutPage.stream()
                .map(MobileElement::getText)
                .map(String::trim)
                .anyMatch(text -> containsParagraph.equals(text));

        // Trigger Exception in case of a fail
        if(match){
            logger.info("We have a match: [" + containsParagraph + "] exists inside HOW TO PLAY view");
        }else{
            throw new IOException("We don't have a match");
        }
    }
}
