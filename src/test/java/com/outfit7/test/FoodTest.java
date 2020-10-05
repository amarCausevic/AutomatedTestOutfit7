package com.outfit7.test;

import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(FoodTest.class);
    @Test
    void feedTomWithFoodItem() throws InterruptedException, Exception {
        MobileElement clickOnFoodButton = driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton"));
        // Check if we are in main room
        if(clickOnFoodButton != null){
            // Open food item menu
            clickOnFoodButton.click();
            logger.info("Clicked on food button");
            MobileElement foodItem =  driver.findElement(By.id("com.outfit7.talkingtom:id/foodItemChilly"));
            logger.info("Food item is set, menu of food items is visible");

            // Check that food item menu is opened
            // Click on chilli item
            // Take a screenshot
            if(foodItem != null){
                Thread.sleep(1000);
                foodItem.click();
                logger.info("Food item was pressed");
                Thread.sleep(1000);
                takeScreenShot("TomEatingFood.jpg");
            }else{
                throw new IOException("MobileElement (FOOD ITEM BUTTON) was not found or does not exists");
            }
        }else{
            throw new IOException("MobileElement (FOOD BUTTON) was not found or does not exists");
        }
    }


}
