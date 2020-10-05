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
## Appium Automation setting 
For inspector session I had to create costum set of capabilities for Automatic Server. 
Appium Desktop server will run on http://localhost:4723

Parameter    | Type | Value
------------ | ---- | -------
deviceName   | text | SM_G935F
platformName | text | Android
udid         | text | ce11160b0c4cd20b03
appPackage   | text | com.outfit7.talkingtom
appActivity  | text | com.outfit7.talkingtom.Main

