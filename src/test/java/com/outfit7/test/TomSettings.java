package com.outfit7.test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.io.IOException;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TomSettings extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TomSettings.class);

    @Test
    void knockOutTom() throws Exception {
        MobileElement foodButton = driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton"));
        if(foodButton != null){
            logger.info("We are in main room, proceed to K.O.");
            for(int i = 0; i <= 10; i++){
                pressActionMiddleOfScreen();
                Thread.sleep(250);
            }
            Thread.sleep(2500);
            logger.info("Tom is knocked down");
            takeScreenShot("TomIsKnockedDown.jpg");
        }
    }

    @Test
    void disableViolenceOnTom() throws Exception{
        // First we are going to open How to play page
        driver.findElement(By.id("com.outfit7.talkingtom:id/buttonInfo")).click();
        logger.info("Info button was pressed");
        driver.findElement(By.id("com.outfit7.talkingtom:id/infoWebButtonMoreSettings")).click();
        logger.info("Settings button was pressed");
        List<MobileElement> checkBoxListSettings = driver.findElements(By.className("android.widget.CheckBox"));

        if(checkBoxListSettings.isEmpty()){
            logger.error("No elements were found on the page");
            throw new IOException("No elements weer found on the page");
        }else{
            logger.info("List of checkbox is not empty proceeding to disable or enable violence");
            disableOrEnableViolence(checkBoxListSettings);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
            logger.info("Back button was pressed, we are back in the home screen");
            //Click on TOM to trigger animation
            Thread.sleep(1000);
            pressActionMiddleOfScreen();
            logger.info("You are cuddling TALKING TOM");
            Thread.sleep(1000);
            takeScreenShot("CuddlingTom.jpg");
        }
    }

    private boolean isChecked(MobileElement item){
        boolean checkboxState;
        if(item.getAttribute("checked").equals("true")){
            return checkboxState = true;
        }else{
            return  checkboxState = false;
        }
    }

    // 0 -> Disable VIOLENCE
    private void disableOrEnableViolence(List<MobileElement> checkboxItems) throws Exception{
        int index = 0;
        for (MobileElement item : checkboxItems) {
            if(index++ == 0){
                if(isChecked(item)){
                    item.click();
                    logger.info("Violence to KO TOM will be disabled, CUDDLES time enabled");
                }else if(!isChecked(item)){
                    logger.info("Violence to KO TOM will be enabled, proceed to UPPERCUT TOM");
                }else{
                    throw new IOException("Cannot set setting");
                }
            }
        }
    }

    private void pressActionMiddleOfScreen(){
        AndroidTouchAction touchAction;
        Dimension windowDimension = driver.manage().window().getSize();
        double x;
        double y;

        x = windowDimension.getWidth() * 0.5;
        y = windowDimension.getHeight() * 0.5;

        touchAction = new AndroidTouchAction(driver);
        touchAction.press(PointOption.point((int)x,(int)y)).release().perform();
    }
}
