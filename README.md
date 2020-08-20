## Messaging Dashboard
The aim of this project is to build a simple web application that allows one user to send a short message to another user on several different platforms simultaneously. It will start with simply being capable of sending a simultaneous SMS message and email, but the goal is to add more platforms (e.g Slack, Discord, [etc.](https://www.capterra.com/team-communication-software/)) via microservices using their respective web API. A key aspect is that users' contact information will be hidden and secure from other users, allowing easy contact in many forms without the need to make that information public.

## Introduction
This project primarily exists for me to familiarize myself with the technology, frameworks, and techniques used to create it. Conceptually, it is an easy project to think of more features to add, meaning more lessons to learn and skills to develop.

The functionality of the app itself would be a useful way to make sure important messages are seen by someone that may not consistently check their phone, email, or whatever messaging platform a professional team may be using.

## Technology/Frameworks
- Spring Boot 2.33
  - Security
  - Web
  - JDBC
  - H2 DB
- Angular 10.0.11
  - CLI
  - Materials
- Amazon Web Services
  - TBD (most likely to be hosted by Elastic Beanstalk)
- Node 12.18.3
- Java 11
- Maven

## Launch Locally
1. In the top-level directory run `./mvnw generate-resources` to ensure modules are installed. (See [this helpful repo](https://github.com/dsyer/spring-boot-angular) for details)
2. Run `./mvnw spring-boot:run`. The Angular front end should automatically be built and pushed to target/classes by the Maven build.
3. Navigate to [http://localhost:8080/](http://localhost:8080/)
