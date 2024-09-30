# API Automation Framework

This project automates the verification of user tasks using Rest Template and JUnit. The scenario involves checking that all users from the city `FanCode` have completed more than half of their todo tasks.

## Scenario

- **Given**: User has todo tasks
- **And**: User belongs to the city `FanCode`
- **Then**: User's completed task percentage should be greater than 50%

`FanCode` city can be identified by latitude between `-40` to `5` and longitude between `5` to `100` in the users API.


## Description

```
Java 11
Spring-boot: 2.6.7
Gradle
```

## Setup

1. Clone the repository:
   ```sh
   gh repo clone srilaxmi23670/TestAutomation
   cd TestAutomation

## How to run

| Description        | Command                 |
|:-------------------|:------------------------|
| Build dependencies | `./gradlew clean build` |
| Run unit tests     | `./gradlew test`        |
| Run application    | `./gradlew bootRun`     |

## Execution curl for results
   ```sh
   curl --location 'http://localhost:8080/api/v1/users/todo-percentage
   ```

## Test Case Execution
The test verifies the following:

1. Fetches users from the city FanCode based on latitude and longitude conditions and verifies the status code and response.
2. Checks that all users from FanCode city have completed more than 50% of their todos.
3. Returns the UserActivity of tasks completion with percentage


This `README.md` file provides comprehensive instructions for setting up and running the tests.
