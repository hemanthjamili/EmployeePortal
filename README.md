# Employee Portal

This is an Employee Portal developed as part of Hiring Process for Societe Generale. Technologies used are J2EE, Spring Boot, Hibernates, AngularJS, HTML, CSS.

## Getting Started

For the sake of simplicity and easy to run, I have used H2 in-memory Database. To Get Started, kinldy download/clone the repository for running the application in your local system.

### Prerequisites

Your local system mush have JDK1.8

### REST API

There are four Rest-end points for this application. The context-path is set as "/". Hence for home page, kindly access http://localhost:8080

* To insert a new employee - http://localhost:8080/api/employee - POST method
* To update an existing employee - https://localhost:8080/api/employee/{id} - PUT method
* To delete an existing employee - https://localhost:8080/api/employee/{id} - DELETE method
* To fetch all employees - http://localhost:8080/api/employee - GET method


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Naga Sai Hemanth Jamili** - *Initial work* - [hemanthjamili](https://github.com/hemanthjamili)
