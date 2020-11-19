# Wex Challenge
Project developed using Java, Selenium, Cucumber and uploaded to Git for versioning

## Prerequisites
Selenium, Cucumber and JUnit dependencies are set into the pom.xml file as dependencies, meaning you need to run maven commands for it.
```
mvn clean test 
mvn install
```
### Installing and Running
This project was entirely built using IntelliJ with Cucumber For Java plugin 
```
(https://plugins.jetbrains.com/plugin/7212-cucumber-for-java)
```

- It was executed through the IDE, right-clicking the .feature file and select Run. 
- Going inside the feature, it's also possible to run each scenario at the time. 
- At the lines where Scenario starts, the plugin marks right beside the line number a Run button.
- Results, possible failures and prints of what was found at each scenario will be displayed into the Terminal. 

## Project Detailing
### Details
Four scenarios are automated in this code, running through Java and Selenium. Cucumber is the forefront layer.
#### Scenario @ID-0001 - Test Setup
In this scenario, open the browser and navigate to Google's search page, 
fill it with "Amazon Brasil" and the result with "www.amazon.com.br" should be clicked.

--- 
#### Scenario @ID-0002 - 80% Of Shown Products Should Be Exclusively The Searched Product
 For this scenario, browse to Amazon's webpage, locate the Amazon's search bar, type "iPhone" and submit. 
 Count how many products are in the result list, how many of its items starts with "iPhone" name and how many contains the keyword "iPhone". 
 Then validate if the number of items which the name starts with iPhone is greater than 30% of total items and if the number of items that contains the keyword "iPhone" is greater than 80% of total items.
 It seems that there are mixed information on Wex_Challenge.pdf file related to 30% and 80% thresholds. Both validations were created.
--- 
#### Scenario @ID-0003 - The Higher Price In The First Page Can't Be Greater Than U$2000    
 Navigate to Amazon and search for "iPhone", compare and find what is the most expensive item which its name starts with "iPhone"
 Then convert its value to US$ using an external API (https://exchangeratesapi.io/) and make sure the converted value is under US$2000

--- 
#### Scenario @ID-0004 - Products Different Than The Searched Product Should Be Cheaper Than The Searched Product
 Navigate to Amazon and search for "iPhone", compare and find what is the most expensive item which its name does not start with "iPhone"
 Then make sure its value is cheaper than the cheapest item which its name starts with "iPhone". 
 During internal tests, this scenario was failing due to one item which does not have "iPhone" on its name and costs over than R$8,000


--- 
- ``Felipe Barbosa``
- ``Nov 2020``