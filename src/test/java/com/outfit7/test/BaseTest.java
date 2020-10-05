package com.outfit7.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseTest {

    AppiumDriver<MobileElement> driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    @BeforeAll
    public void initTest() throws Exception {
        logger.info("Initializing testing");
        startAppiumConnection();
        prepareState();
    }
    
    private void startAppiumConnection() throws Exception {
        // Set driver capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("fullReset", "false");
        capabilities.setCapability("deviceName", "Android Device");
        capabilities.setCapability("platformName", "Android");
        File file = new File("src/main/resources/base.apk");
        String absolutePath = file.getAbsolutePath();
        capabilities.setCapability("app", absolutePath);

        // Create new android driver, the default port for Appium is 4723
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        // Default appium timeout for searching elements
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    private void prepareState() throws InterruptedException, IOException, NotFoundException {
        Dimension windowSize = driver.manage().window().getSize();

        // Wait for first element so we can continue
        driver.findElement(By.id("com.outfit7.talkingtom:id/sharingAgeScreeningButtonOk"));

        // Swipe to get to some age
        String command = String.format("adb shell input swipe %1$s %2$s %3$s %4$s %5$s", windowSize.width * 0.5, windowSize.height * 0.5, windowSize.width * 0.5,
                windowSize.height * 0.7, 1000);
        Runtime.getRuntime().exec(command);

        // Wait for animation to end
        Thread.sleep(2000);

        // Click button ok to continue
        driver.findElement(By.id("com.outfit7.talkingtom:id/sharingAgeScreeningButtonOk")).click();

        // Sometimes we get asked for special permission
        MobileElement allowPermissionButton = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
        if (allowPermissionButton != null) {
            allowPermissionButton.click();
        }

        // Pass gdpr screen
        driver.findElement(By.id("com.outfit7.talkingtom:id/btnYes")).click();

        // Check if we are in main room
        driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton"));
        // System.out.println("###We are in main room###");
        logger.info("We are in main room");
    }

    public void takeScreenShot(String nameOfScreenShot) throws Exception{
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(nameOfScreenShot));
        if(file.exists()){
            logger.info("Screenshot has been taken");
        }else{
            logger.error("Screenshot was not taken");
        }
    }
}
