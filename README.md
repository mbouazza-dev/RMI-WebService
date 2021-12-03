<h1 align="center"> RMI-WebService</h1>

<p align="center">
RMI-WEBSERVICES is a Java application for employees to buy and resell products. Employees can make purchases in multiple currencies, use a banking service, and use a foreign service to open the app to other users.
</p>


## Development

### Prerequisites

To run RMI-WebService you'll need the following tools installed

- [`Java JDK8`](https://www.oracle.com/fr/java/technologies/javase/javase8-archive-downloads.html)
- [`Maven`](https://maven.apache.org/)

### Installation

WIP...


## Architecture

<img src="./docs/global_project_architecture.jpeg" alt="Development architecture" width="620px" />

RMI-WebService is organized through differents J2E applications, RMI architecture and SOAP services:

- [eiffel-corp](https://github.com/mbouazza-dev/RMI-WebService/tree/main/eiffel-corp)

- [ifshare-app](https://github.com/mbouazza-dev/RMI-WebService/tree/main/ifshare-app)

- [bank-service](https://github.com/mbouazza-dev/RMI-WebService/tree/main/bank-service)

- [if-service](https://github.com/mbouazza-dev/RMI-WebService/tree/main/if-service)

- [converter](http://webservices.currencysystem.com/currencyserver/)

### Eiffel-Corp 

Eiffel Corp is a Client application allowing employees to access company services including IfShare App.
It write with Spring framework and use Java RMI architecture to access IfShare-app features like the store and the mail notifier. 

Eiffel Corp use Thymeleaf and Spring to give a Web User Interface in order to sell and buy products. Employees must enter their username and password to access Eiffel Corp, Also, when a product is no longer on sale, it is possible to register in order to receive a notification during the restock.

The employee datas like email, firstname or login are stored in H2 database.

## Goals
Build multiple web application based on java RMI allowing the sale of products between individuals and company members

## Build

```shell
mvn package # in root directory
```

## Import this project in Eclipse

1. File > Import...
2. Maven > Existing Maven Projects
3. Browse to the root directory
4. Check that all modules are checked
5. Finish

