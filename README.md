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

# Test scenarios

## Feed Tom with food item
Step     | Step Description                                             | Status    
-------- | ------------------------------------------------------------ | ------------------------------------------------
Step 1   | Trigger click event on food button to open food item menu    | :white_check_mark: Step passed with success
Step 2   | Trigger click event on food item to open food item menu      | :white_check_mark: Step passed with success
Step 3   | Take screenshot of character eating food item                | :white_check_mark: Step passed with success
Overall  | N/A                                                          | :white_check_mark: Step passed with success

Result: In case of success screenshot will be available in src folder, on GUI side user will see character eating food item. In case of failure exception will be thrown for futher investigation.

## Check if paragraph exists in HOW TO PLAY view
Step     | Step Description                                                  | Status    
-------- | ----------------------------------------------------------------- | ------------------------------------------------
Step 1   | Trigger click event info button to open info view                 | :white_check_mark: Step passed with success
Step 2   | Trigger click event on how to play button to open view            | :white_check_mark: Step passed with success
Step 3   | Search for string "Talk to Tom and he repeats with a funny voice" | :white_check_mark: Step passed with success
Overall  | N/A                                                               | :white_check_mark: Step passed with success

Success: Log ""We have a match: [" + containsParagraph + "] exists inside HOW TO PLAY view");" will be visible
Fail: IOException

## Custom test 
### Knocked down character (Violence setting is ON)
Step     | Step Description                                                  | Status    
-------- | ----------------------------------------------------------------- | ------------------------------------------------
Step 1   | Check if we are in MAIN room                                      | :white_check_mark: Step passed with success
Step 2   | Calculate middle of window dimension                              | :white_check_mark: Step passed with success
Step 3   | Trigger press action on character x11 times                       | :white_check_mark: Step passed with success
Overall  | N/A                                                               | :white_check_mark: Step passed with success

Success: Character will be "poked" x11 times, when press action is finished knock down animation is triggered. At the end screenshot is taken of current status on GUI.
Fail: IOException

### Pet character (Violence setting is OFF)
Step     | Step Description                                                  | Status    
-------- | ----------------------------------------------------------------- | ------------------------------------------------
Step 1   | Check if we are in MAIN room                                      | :white_check_mark: Step passed with success
Step 2   | Trigger click action on info button                               | :white_check_mark: Step passed with success
Step 3   | Trigger click action on setting button                            | :white_check_mark: Step passed with success
Step 4   | Disable setting Violence (Can I hit Tom?)                         | :white_check_mark: Step passed with success
Step 5   | Return back to main room                                          | :white_check_mark: Step passed with success
Step 5   | Trigger press action on character                                 | :white_check_mark: Step passed with success

Success: Animation of character being petted will be triggered, at the end screenshot will be taken of current status on GUI.


# Solution for my expertise test
## 1.) Go to info,press show to play and assert that first paragraph is correct “Talk to Tom and he repeats with a funny voice”
- [X] Create new class "HowToPlay.java" with @TestInstance(TestInstance.Lifecycle.PER_CLASS)
- [X] Create new method with @Test
- [X] First find an element for info button. ID of an element is com.outfit7.talkingtom:id/buttonInfo
- [X] Second find an element for how to play button. ID of an element is com.outfit7.talkingtom:id/infoWebButtonHowToPlay
- [X] Trigger click event on buttonInfo button 
- [X] After we are in INFO view we can click on infoWebButtonHowToPlay
- [X] Tigger click event on infoWebButtonHowToPlay
- [X] HOW TO PLAY view will be open
- [X] Save all android.view.View inside a List<MobileElement>
- [X] Go through list and find item with correct text 

  
## 2.) Feed him with some food, make sure he is indeed eating something and take screenshot
- [X] Create new class "FoodTest.java" with @TestInstance(TestInstance.Lifecycle.PER_CLASS)
- [X] Create new method with @Test
- [X] Get food button element with. ID of an element is com.outfit7.talkingtom:id/foodButton
- [X] Check if button is not null and proceed to click on button
- [X] Find one item (In my case I choose food time chilli). ID of an element= com.outfit7.talkingtom:id/foodItemChilly
- [X] If food item is not null procced to trigger click event on button 
- [X] Take a screenshot of eating animation


## 3.) Create a custom test scenario
- [X] Create new class "FoodTest.java" with @TestInstance(TestInstance.Lifecycle.PER_CLASS)
- [X] Create new method with @Test, method will trigger poke until knockdown
- [X] Create new method with @Test, method will trigger press action which will trigger pet down animation
  - [X] Violence is ON
    - [X] Check that we are in main room
    - [X] Create loop that press action will be triggered x11 times
    - [X] Take a screenshot
  - [X] Violence is OFF
    - [X] Check that we are in main room
    - [X] Get id for info button. ID of an element is com.outfit7.talkingtom:id/buttonInfo 
    - [X] Trigger click event on element 
    - [X] Once we are in INFO view get id for settings button. ID for an element is com.outfit7.talkingtom:id/infoWebButtonMoreSettings
    - [X] After settings view is displayed get all checkbox element into List of MobileElement. ID for an element is android.widget.CheckBox
    - [X] Craete a foreach so we can find first element with index 0 
    - [X] Check if button is checked
    - [X] Trigger click event 
    - [X] Trigger click event on BACK BUTTON. xPath of an element is //android.widget.ImageButton[@content-desc=\"Navigate up\"]"
    - [X] Tigger press event on the middle of the screen
    - [X] Take a screenshot of current GUI status




                

