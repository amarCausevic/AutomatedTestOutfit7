# AutomatedTestOutfit7
Unit testing for Talking Tom application (Android)

# Setup
- IntelliJ
- Appium Desktop Server v1.18.0
- log4j2
- Android SDK
- adb
- Java SDK
- Enviroment: macOS Catalina Version 10.15.7


# Hardware
- Macbook pro 13
- Android device: Samsung Galaxy S7 Edge

# Appium 
## Connection between GUI and Backend 
My local server was running on 0.0.0.0 local ip(local host), with default port 4723. 
## Appium Automation Setting 
For inspector session I had to create costum set of capabilities for Automatic Server. 
Appium Desktop server will run on http://localhost:4723

Parameter    | Type | Value
------------ | ---- | -------
deviceName   | text | SM_G935F
platformName | text | Android
udid         | text | ce11160b0c4cd20b03
appPackage   | text | com.outfit7.talkingtom
appActivity  | text | com.outfit7.talkingtom.Main

# Classes
Class           | Method                         | Type    | Description
------------    | ------------------------------ | ------- | -------------------------
BaseTest.java   | initTest()                     | Void    | Initial method which will execute two methods (startAppiumConnection() and prepareState()) 
BaseTest.java   | startAppiumConnection()        | Void    | It will set capabilities for Appium connection.
BaseTest.java   | prepareState()                 | Void    | It will confirm AGE, allow microphone access, pass gdpr scren and at the end it will trigger click event on foodButton
BaseTest.java   | takeScreenShot()               | Void    | Used to take screenshot on device of current state in the application
FoodTest.java   | feedTomWithFoodItem()          | Void    | Test method which will trigger click event on foodButton and foodItemChilly. At the end screenshot "event" will be executed to take a snap shot of current state in the application
HowToPlay.java   | findElementForInfo()          | Void    | Test method which will trigger click event until GUI displayes HOT TO PLAY view. At the end we assess or evaluate if paragraph "Talk to Tom and he repeats with a funny voice" exists in current view
TomSettings.java   | knockOutTom()               | Void    | Test method which will trigger press action on the middle of the screen so TOM is knocked down
TomSettings.java   | disableViolenceOnTom()      | Void    | Test method which will trigger that violence mode is disabled at the end press event is triggered to assess if violence mode is disabled
TomSettings.java   | isChecked()                 | Boolean | Method returns true or false if element (android.widget.CheckBox) is checked  or not (This could be moved to BaseTest.java class)
TomSettings.java   | disableOrEnableViolence()   | Void | Method will find first element in the list of (android.widget.CheckBox) and select first checkbox and disable it. 
TomSettings.java   | pressActionMiddleOfScreen() | Void | Method will calculacte center of the screen and execute press touch action








                

