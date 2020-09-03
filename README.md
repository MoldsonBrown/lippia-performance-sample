# LIPPIA Performance Example Project

## System Requirements : 
+ git client: https://www.atlassian.com/git/tutorials/install-git 
+ Jmeter 5.3: https://jmeter.apache.org/download_jmeter.cgi
+ docker 18.09: https://docs.docker.com/install/linux/docker-ce/ubuntu/ 
+ docker compose 1.24: https://docs.docker.com/compose/install/

## Docker stack

The following project includes the basic Docker Lippia Containers to run this  web sample project. You can choose to run the code from your favourite IDE, run from console or from Jenkins using the Docker Stack.
To install and start a local instalation with Docker containers go to **Getting started** at the end of this guide. 

# Purpose: 

The following project has the purpose of demonstrate and let test automation developers to to test APIs requests using Lippia Automation Framework based on Rest Client library and check the Performance of the requests. 
This sample project includes the required components as binaries, docker containers and configuration files to simply download and run a set of sample tests in your local computer, using the Lippia container stack described bellow.

# Getting started

## Executing tests in you local machine 
- go to root project folder and you will find a pom.xml file
- run the following command : 
```
mvn clean test
```
	
## Project structure

A typical Lippia Test Automation project usually looks like this 

```
	.
├── main
│   ├── java
│   │   └── com
│   │       └── crowdar
│   │           └── api
│	│				├── config
│	│				│	├── EntityConfiguration.java
│   │               ├── model
│   │               │   ├── Data.java
│	│				│	├── User.java
│   │               │   └── UserCreated.java
│   │               └── services
│   │                   └── UserService.java
│   └── resources
│       ├── config.properties
│       ├── cucumber.properties
│       └── log4j.properties
└── test
    ├── java
    │   ├── ApiExampleProjectParallelTestRunner.java
    │   ├── ApiExampleProjectTestRunner.java
    │   └──
	└── steps	
	│   └── UserSteps.java
	└── apiExampleProject
    │           ├── Hooks.java
    └── resources
        └── features
            └── ApiExample.feature
		└── jsons
            └── createUser.json
```

Folder's description:

|Path   |Description    |
|-------|----------------|
|main\java\\...\examples\model\\\*.java|Folder with all the **Mapped Objects** matching steps with java code|
|main\java\\...\examples\config\\\*Steps.java|Folder with all the **Settings** wich match with Gherkin Test Scenarios |
|test\resources\features\\\*.feature|Folder with all the **feature files** containing **Test Scenarios** and **Sample Data** |
|main\resources|Folder with all configuration needed to run Lippia |

In this example, *ApiExample* is the first endpoint the framework will interact with. The **steps** defined in *UserSteps.java* to execute the *Test Scenarios* defined in Gherkin language. 


|File   | Description    |
|-------|----------------|
|User.java   | Model: you can declare every attribute expected on the responses that you want to interact with. You need to add one new file for each response you want to model in your tests. |
|UserSteps.java   | StepOpject: Code to support the behaviour of each **step** coded into the feature files for the *User* endpoint. This code executes the interaction between the Framework and the api endopoint and match the steps with the code who run interactions. |
|ApiExample.feature| Feature file: Definition of the **Test Scenarios** with all the **steps** written in Cucumber format (http)|



# Scenario Example

The scenarios can be written using BDD methodology. 
	
	Given as a precondition
	
	When as actions
	
	Then as validations
	
	
On each declared step you can insert the calls defined from service classes

# Json Structure for request data

This project use json to manage request data like url parameters, body data, headers and url endpoints. The following picture shows the structure of the json



# Getting started
    
- If you are Linux user 
    [`Getting started - Linux User`](https://bitbucket.org/crowdarautomation/lippia-performance-example-project/src/master/docs/README_Linux.md)
- If you are Windws user
    [`Getting started - Windows User`](https://bitbucket.org/crowdarautomation/lippia-performance-example-project/src/master/docs/README_Windows.md)