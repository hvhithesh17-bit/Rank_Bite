<<<<<<< HEAD
# RankBite - Food Delivery Analytics & Restaurant Ranking System

## Project Overview
RankBite is a robust Java-based application designed to analyze food delivery metrics and provide intelligent restaurant rankings based on configurable weights. This project demonstrates core Object-Oriented Programming principles, custom exceptions, file I/O, collections, and Java Swing for the graphical user interface.

## Team Responsibilities

- **Hithesh (Core Java & Ranking System)** 
  - Implementation of Model classes (OOP: Abstraction, Inheritance).
  - Configurable ranking algorithm using the Strategy Design Pattern.
  - Custom exceptions and CSV Data parsing.
  - Complete Java Swing Graphical User Interface.

- **Dhanush (Database & JDBC) - *To Be Implemented***
  - Aiven Cloud MySQL integration.
  - Database schema and JDBC DAO classes.

- **Nikhil (Analytics & Reports) - *To Be Implemented***
  - SQL Analytics and generation of statistical reports.

## Features Developed in this Branch (`core-ranking`)
- **Configurable Weighted Ranking:** Ranks restaurants balancing rating (40%), delivery performance (25%), price (15%), and demand (20%).
- **Data Normalization:** Maps diverse metrics to a unified 0-100 score dynamically without hardcoded bounds.
- **Swing UI:** Fully functional interface including Login, Dashboard, Restaurant data table, and Ranking dashboards.
- **File I/O:** Reads data safely from CSV.
- **Exception Handling:** Graceful error handling for missing data or invalid metric ranges.

## Technology Stack
- Java 21
- Apache Maven
- Java Swing (GUI)
- JUnit 5 (Testing)

## How to Run the Project
1. Open terminal in the project directory.
2. Compile and package the code using Maven:
   ```bash
   mvn clean compile
   ```
3. Run the application:
   ```bash
   java -cp target/classes com.rankbite.Main
   ```
4. **Demo Login:** Username: `admin` | Password: `1234`

## How to Run Tests
1. Execute the JUnit 5 tests via Maven:
   ```bash
   mvn test
   ```

## OOP Concepts Demonstrated
- **Inheritance & Abstraction:** An abstract `Person` class extended by `User` and `Customer`.
- **Polymorphism:** The `RankingStrategy` interface implemented by `WeightedRankingStrategy` and `RatingOnlyStrategy`.
- **Encapsulation:** Model classes protect data with private fields and public getters/setters.
=======
# Rank_Bite
RankBite – Food Delivery Analytics &amp; Restaurant Ranking System is a Java-based desktop application that analyzes food delivery data and ranks restaurants using rating, delivery performance, price, and customer demand. The project uses Java Swing, OOP, Collections, CSV/File I/O, JDBC, and MySQL, with analytics and report generation features.
>>>>>>> 63e2970154631d75e538a96e8be9526ec7bfe417
