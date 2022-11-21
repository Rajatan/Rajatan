#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#@tag
#Feature: Title of your feature
  #I want to use this template for my feature file
#
  #@tag1
  #Scenario: Title of your scenario
    #Given I want to write a step with precondition
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
      
      
Feature: Validate APIs
#let me twik the feature to DataDriven i.e., lets pass the data to name, langauge and address field of addPlace API payLoad from feature
#file, using Scenario Outline and Examples 
	
	@addPlace @Regression
	Scenario Outline: Verify place is being successfully added using AddPlaceAPI
     Given Add place payload "<name>" "<langauge>" "<address>"
     When User calls "AddPlaceAPI" with "POST" http request
     Then The API call got success with status code 200
     And "status" in response body is "OK"
     And "scope" in response body is "APP"
     And  Validate the "<name>" with "GetPlaceAPI"
  
  Examples:
  	|name				|langauge			|address			|
  	|Keshav			|Marathi			|Pune					|
  #	|Madhav			|Gujrathi			|Gujrath			|
     
     
  @deletePlace @Regression
  Scenario: Verify whether deletePlace functionality is working
  	Given DeletePlace Payload
  	When User calls "DeletePlaceAPI" with "POST" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
      
      
      
      
      
      
      
